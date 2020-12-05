/** @jsxImportSource @emotion/react */
import React, { useEffect, useState } from "react";
import Contract from "../model/Contract";
import * as DocumentService from "../service/DocumentService";
import PresentationElementView from "../component/PresentationElementView";
import { DocumentEditorContext } from "../context/DocumentEditorContext";
import { extendPresentationElements } from "../service/ProjectService";
import { Col, Container, Row } from "react-bootstrap";
import { css } from "@emotion/react";
import { DragDropContext, Draggable, Droppable, DropResult } from "react-beautiful-dnd";
import { List } from "immutable";
import { AtomicInteger } from "../utils/AtomicInteger";
import { ExtendedSectionPresentationElement } from "../model/ExtendedSectionPresentationElement";
import { ExtendedRuleInvocationPresentationElement } from "../model/ExtendedRuleInvocationPresentationElement";
import { ExtendedPresentationElement } from "../model/ExtendedPresentationElement";
import { moveItemInList } from "../utils/ListUtils";
import MetaValue from "../model/MetaValue";

const Style = {
  title: css`
    text-align: center;
    font-size: 20px;
    font-weight: bold;
    padding: 10px 0;
  `,
  holder: css`
    width: 40px;
    min-width: 40px;
    border: 1px solid;
  `,
  nestedElement: css`
    flex-grow: 1;
  `,
};

export class DocumentEditorElement {
  id: string;

  protected constructor(id: string) {
    this.id = id;
  }
}

export class DocumentEditorSectionElement extends DocumentEditorElement {
  title: string;
  documentEditorElements: List<DocumentEditorElement>;

  constructor(id: string, title: string, documentEditorElements: List<DocumentEditorElement>) {
    super(id);
    this.title = title;
    this.documentEditorElements = documentEditorElements;
  }

  withDocumentEditorElements(documentEditorElements: List<DocumentEditorElement>): DocumentEditorSectionElement {
    return new DocumentEditorSectionElement(this.id, this.title, documentEditorElements);
  }
}

export class DocumentEditorRuleInvocationElement extends DocumentEditorElement {
  extendedPresentationElement: ExtendedRuleInvocationPresentationElement;

  constructor(id: string, extendedPresentationElement: ExtendedRuleInvocationPresentationElement) {
    super(id);
    this.extendedPresentationElement = extendedPresentationElement;
  }

  withRuleInvocationArguments(ruleInvocationArguments: List<MetaValue>): DocumentEditorRuleInvocationElement {
    return this.withExtendedPresentationElement(this.extendedPresentationElement.withRuleInvocationArguments(ruleInvocationArguments));
  }

  withExtendedPresentationElement(
    extendedPresentationElement: ExtendedRuleInvocationPresentationElement
  ): DocumentEditorRuleInvocationElement {
    return new DocumentEditorRuleInvocationElement(this.id, extendedPresentationElement);
  }
}

var count = 0;

const ContractPage = () => {
  console.log(count);
  count = count + 1;
  const contractId = "2";
  const projectId = "1";
  const [contract, setContract] = useState<Contract | undefined>(undefined);
  const [elements, setElements] = useState<List<DocumentEditorElement> | undefined>(undefined);
  const [nextElementId, _] = useState(new AtomicInteger());
  useEffect(() => {
    DocumentService.getDocument<Contract>(contractId).then(setContract);
  }, []);
  useEffect(() => {
    if (contract === undefined) {
      setElements(undefined);
    } else {
      extendPresentationElements(projectId, contract.presentationElements).then((extendedPresentationElements) => {
        setElements(mergeDocumentEditorElementsAndExtendedPresentationElements(extendedPresentationElements, elements));
      });
    }
  }, [contract]);

  const mergeDocumentEditorElementsAndExtendedPresentationElements = (
    extendedPresentationElements: List<ExtendedPresentationElement>,
    elements?: List<DocumentEditorElement>
  ): List<DocumentEditorElement> => {
    return extendedPresentationElements.map((extendedPresentationElement, index) =>
      mergeDocumentEditorElementAndExtendedPresentationElement(extendedPresentationElement, elements?.get(index))
    );
  };

  const mergeDocumentEditorElementAndExtendedPresentationElement = (
    extendedPresentationElement: ExtendedPresentationElement,
    element?: DocumentEditorElement
  ): DocumentEditorElement => {
    if (extendedPresentationElement instanceof ExtendedRuleInvocationPresentationElement) {
      return mergeDocumentEditorRuleInvocationElementAndExtendedRuleInvocationPresentationElement(extendedPresentationElement, element);
    } else if (extendedPresentationElement instanceof ExtendedSectionPresentationElement) {
      return mergeDocumentEditorSectionElementAndExtendedSectionPresentationElement(extendedPresentationElement, element);
    } else {
      throw Error("ExtendedPresentationElement type is not supported");
    }
  };

  const mergeDocumentEditorRuleInvocationElementAndExtendedRuleInvocationPresentationElement = (
    extendedPresentationElement: ExtendedRuleInvocationPresentationElement,
    element?: DocumentEditorElement
  ): DocumentEditorRuleInvocationElement => {
    const id = element instanceof DocumentEditorRuleInvocationElement ? element.id : nextElementId.next().toString();

    return new DocumentEditorRuleInvocationElement(id, extendedPresentationElement);
  };

  const mergeDocumentEditorSectionElementAndExtendedSectionPresentationElement = (
    extendedPresentationElement: ExtendedSectionPresentationElement,
    element?: DocumentEditorElement
  ): DocumentEditorSectionElement => {
    const id = element instanceof DocumentEditorSectionElement ? element.id : nextElementId.next().toString();
    const elements = element instanceof DocumentEditorSectionElement ? element.documentEditorElements : undefined;

    return new DocumentEditorSectionElement(
      id,
      extendedPresentationElement.title,
      mergeDocumentEditorElementsAndExtendedPresentationElements(extendedPresentationElement.nestedExtendedPresentationElements, elements)
    );
  };

  const getListStyle = (isDraggingOver: boolean) => ({
    background: isDraggingOver ? "lightblue" : "lightgrey",
  });

  const getItemStyle = (isDragging: boolean, draggableStyle: any) => ({
    // some basic styles to make the items look a bit nicer
    userSelect: "none",
    padding: 16,
    margin: `0 0 8px 0`,

    // change background colour if dragging
    background: isDragging ? "lightgreen" : "grey",

    // styles we need to apply on draggables
    ...draggableStyle,
  });

  const getElementFromSection = (
    elements: List<DocumentEditorElement>,
    sectionId: string,
    index: number
  ): DocumentEditorElement | undefined => {
    for (let element of elements.toArray()) {
      if (element instanceof DocumentEditorSectionElement) {
        if (element.id === sectionId) {
          return element.documentEditorElements.get(index);
        } else {
          const nestedElement = getElementFromSection(element.documentEditorElements, sectionId, index);
          if (nestedElement) {
            return nestedElement;
          }
        }
      }
    }
  };

  const removeElementFromSection = (
    elements: List<DocumentEditorElement>,
    sectionId: string,
    index: number
  ): List<DocumentEditorElement> => {
    return elements.map((element) => {
      if (element instanceof DocumentEditorSectionElement) {
        if (element.id === sectionId) {
          return element.withDocumentEditorElements(element.documentEditorElements.remove(index));
        } else {
          return element.withDocumentEditorElements(removeElementFromSection(element.documentEditorElements, sectionId, index));
        }
      } else {
        return element;
      }
    });
  };

  const addElementToSection = (
    elements: List<DocumentEditorElement>,
    sectionId: string,
    index: number,
    newElement: DocumentEditorElement
  ): List<DocumentEditorElement> => {
    return elements.map((element) => {
      if (element instanceof DocumentEditorSectionElement) {
        if (element.id === sectionId) {
          return element.withDocumentEditorElements(element.documentEditorElements.insert(index, newElement));
        } else {
          return element.withDocumentEditorElements(removeElementFromSection(element.documentEditorElements, sectionId, index));
        }
      } else {
        return element;
      }
    });
  };

  const onDragEnd = (result: DropResult) => {
    if (!elements || !contract || !result.destination) {
      return;
    }

    if (result.source.droppableId === "droppable") {
      setElements(moveItemInList(elements, result.source.index, result.destination.index));
    } else {
      const elementToMove = getElementFromSection(elements, result.source.droppableId, result.source.index);
      if (elementToMove) {
        setElements(
          addElementToSection(
            removeElementFromSection(elements, result.source.droppableId, result.source.index),
            result.destination.droppableId,
            result.destination.index,
            elementToMove
          )
        );
      }
    }
  };

  return !contract || !elements ? (
    <div />
  ) : (
    <Container>
      <Row>
        <Col>
          <div css={Style.title}>{contract.name}</div>
          <DocumentEditorContext.Provider value={{ projectId: projectId, documentId: contractId }}>
            <DragDropContext onDragEnd={onDragEnd}>
              <Droppable droppableId="droppable" type="top">
                {(provided, snapshot) => (
                  <div {...provided.droppableProps} ref={provided.innerRef} style={getListStyle(snapshot.isDraggingOver)}>
                    {elements.map((element, index) => (
                      <Draggable key={element.id} draggableId={element.id} index={index}>
                        {(provided, snapshot) => (
                          <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            style={getItemStyle(snapshot.isDragging, provided.draggableProps.style)}
                          >
                            <div style={{ display: "flex", padding: "10px" }}>
                              <div css={Style.holder} {...provided.dragHandleProps}>
                                {element.id}
                              </div>
                              <div css={Style.nestedElement}>
                                <PresentationElementView
                                  key={index}
                                  element={element}
                                  onElementChange={(element) => {
                                    setElements(elements.set(index, element));
                                  }}
                                />
                              </div>
                            </div>
                          </div>
                        )}
                      </Draggable>
                    ))}
                    {provided.placeholder}
                  </div>
                )}
              </Droppable>
            </DragDropContext>
          </DocumentEditorContext.Provider>
        </Col>
      </Row>
    </Container>
  );
};

export default ContractPage;
