/** @jsxImportSource @emotion/react */
import React, { useContext, useEffect, useState } from "react";
import Contract from "../model/Contract";
import * as DocumentService from "../service/DocumentService";
import { saveDocument } from "../service/DocumentService";
import PresentationElementView from "../component/presentation-element/PresentationElementView";
import { convertToSmartContract, downloadDocument, extendPresentationElements } from "../service/ProjectService";
import { Button, Col, Row } from "react-bootstrap";
import { css } from "@emotion/react";
import { DragDropContext, Draggable, Droppable, DropResult } from "react-beautiful-dnd";
import { List, Map } from "immutable";
import { AtomicInteger } from "../utils/AtomicInteger";
import { ExtendedSectionPresentationElement } from "../model/ExtendedSectionPresentationElement";
import { ExtendedRuleInvocationPresentationElement } from "../model/ExtendedRuleInvocationPresentationElement";
import { ExtendedPresentationElement } from "../model/ExtendedPresentationElement";
import { moveItemInList } from "../utils/ListUtils";
import MetaValue from "../model/MetaValue";
import PresentationElement from "../model/PresentationElement";
import SectionPresentationElement from "../model/SectionPresentationElement";
import RuleInvocationPresentationElement from "../model/RuleInvocationPresentationElement";
import RuleBrowser from "../component/RuleBrowser";
import Rule from "../model/Rule";
import NaturalLanguageProvision from "../model/NaturalLanguageProvision";
import { OutputMessage } from "../model/OutputMessage";
import Id from "../model/Id";
import { DocumentType } from "../model/Document";
import AddButton from "../common/AddButton";
import { prepareEmptyRuleInvocationArgument } from "../service/RuleService";
import { ProjectContext } from "../context/ProjectContext";
import EditableTextField from "../common/EditableTextField";

const Style = {
  title: css`
    text-align: center;
    font-size: 20px;
    font-weight: bold;
    padding: 10px 0;
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

  withRuleInvocationArguments(ruleInvocationArguments: Map<string, MetaValue>): DocumentEditorRuleInvocationElement {
    return this.withExtendedPresentationElement(this.extendedPresentationElement.withRuleInvocationArguments(ruleInvocationArguments));
  }

  withExtendedPresentationElement(
    extendedPresentationElement: ExtendedRuleInvocationPresentationElement
  ): DocumentEditorRuleInvocationElement {
    return new DocumentEditorRuleInvocationElement(this.id, extendedPresentationElement);
  }
}

const ContractPage = ({ contractDbId }: { contractDbId: string }) => {
  const { projectId } = useContext(ProjectContext);
  const [contractId, setContractId] = useState<Id | undefined>(undefined);
  const [contractName, setContractName] = useState<string | undefined>(undefined);
  const [contractDescription, setContractDescription] = useState<string | null>(null);
  const [elements, setElements] = useState<List<DocumentEditorElement> | undefined>(undefined);
  const [presentationElementsPendingExtending, setPresentationElementsExtending] = useState<List<PresentationElement> | undefined>(
    undefined
  );
  const [nextElementId, _] = useState(new AtomicInteger());
  const [rulesSearchResult, setRulesSearchResult] = useState<List<Rule>>(List());
  const [outputMessages, setOutputMessages] = useState<List<OutputMessage>>(List());
  useEffect(() => {
    let isSubscribed = true;
    DocumentService.getContract(contractDbId).then((contract) => {
      extendPresentationElements(projectId, contract.presentationElements, contract.presentationElements).then((result) => {
        if (isSubscribed) {
          setContractId(contract.id);
          setContractName(contract.name);
          setContractDescription(contract.description);
          setElements((oldElements) =>
            mergeDocumentEditorElementsAndExtendedPresentationElements(result.extendedPresentationElements, oldElements)
          );
          setOutputMessages(result.outputMessages);
        }
      });
    });

    return () => {
      isSubscribed = false;
    };
  }, []);
  useEffect(() => {
    if (presentationElementsPendingExtending === undefined) {
      return;
    }
    let isSubscribed = true;

    extendPresentationElements(projectId, presentationElementsPendingExtending, presentationElementsPendingExtending).then((result) => {
      if (isSubscribed) {
        setElements((oldElements) =>
          mergeDocumentEditorElementsAndExtendedPresentationElements(result.extendedPresentationElements, oldElements)
        );
        setOutputMessages(result.outputMessages);
      }
    });

    return () => {
      isSubscribed = false;
    };
  }, [presentationElementsPendingExtending]);

  const refreshElements = (elements: List<DocumentEditorElement>): void => {
    const presentationElements = extractPresentationElementsFromDocumentEditorElements(elements);
    setElements(elements);
    setPresentationElementsExtending(presentationElements);
  };

  const extractPresentationElementsFromDocumentEditorElements = (elements: List<DocumentEditorElement>): List<PresentationElement> => {
    return elements.map((element) => extractPresentationElementFromDocumentEditorElement(element));
  };

  const extractPresentationElementFromDocumentEditorElement = (element: DocumentEditorElement): PresentationElement => {
    if (element instanceof DocumentEditorSectionElement) {
      return new SectionPresentationElement(extractPresentationElementsFromDocumentEditorElements(element.documentEditorElements));
    } else if (element instanceof DocumentEditorRuleInvocationElement) {
      return new RuleInvocationPresentationElement(element.extendedPresentationElement.presentationElement.ruleInvocation);
    } else {
      throw new Error("Usupported type");
    }
  };

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
    if (!elements || !result.destination) {
      return;
    }

    if (result.source.droppableId === "droppable") {
      refreshElements(moveItemInList(elements, result.source.index, result.destination.index));
    }
    if (result.source.droppableId === "search") {
      const rule = rulesSearchResult.get(result.source.index);
      if (rule) {
        const newElement = new DocumentEditorRuleInvocationElement(
          nextElementId.next().toString(),
          new ExtendedRuleInvocationPresentationElement(
            new RuleInvocationPresentationElement({
              ruleId: rule.id,
              arguments: Map(rule.arguments.map((argument) => [argument.name, prepareEmptyRuleInvocationArgument(argument)])),
            }),
            new NaturalLanguageProvision(""),
            rule,
            Map(),
            List()
          )
        );
        refreshElements(addElementToSection(elements, result.destination.droppableId, result.destination.index, newElement));
      }
    } else {
      const elementToMove = getElementFromSection(elements, result.source.droppableId, result.source.index);
      if (elementToMove) {
        refreshElements(
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

  const convertToContract = (elements: List<DocumentEditorElement>): Contract => {
    return {
      documentType: DocumentType.Contract,
      id: contractId,
      name: contractName,
      description: contractDescription,
      presentationElements: extractPresentationElementsFromDocumentEditorElements(elements),
    } as Contract;
  };

  return !contractName || !elements || !contractId ? (
    <div />
  ) : (
    <DragDropContext onDragEnd={onDragEnd}>
      <Row>
        <Col xs={4}>
          <Row>
            <Col>
              <div css={Style.title}>Rule browser</div>
              <RuleBrowser projectId={projectId} searchResult={rulesSearchResult} onSearchResultChange={setRulesSearchResult} />
            </Col>
          </Row>
          <Row>
            <Col>
              <div>
                <div css={Style.title} style={{ marginTop: "20px" }}>
                  Actions
                </div>
                <div style={{ display: "flex" }}>
                  <Button style={{ flexGrow: 1 }} onClick={() => saveDocument(contractDbId, convertToContract(elements))}>
                    Save
                  </Button>
                  <Button style={{ marginLeft: "15px", flexGrow: 1 }} onClick={() => refreshElements(elements)}>
                    Refresh
                  </Button>
                  <Button
                    style={{ marginLeft: "15px", flexGrow: 1 }}
                    onClick={() => downloadDocument(projectId, convertToContract(elements))}
                  >
                    Generate document
                  </Button>
                  <Button
                    style={{ marginLeft: "15px", flexGrow: 1 }}
                    onClick={() => convertToSmartContract(projectId, convertToContract(elements))}
                  >
                    Smart contract
                  </Button>
                </div>
              </div>
            </Col>
          </Row>
          <Row>
            <Col>
              <div>
                <div css={Style.title} style={{ marginTop: "20px" }}>
                  Compilation errors
                </div>
                <div style={{ overflowY: "auto" }}>
                  {outputMessages.map((message, index) => (
                    <div key={index}>{message.message}</div>
                  ))}
                </div>
              </div>
            </Col>
          </Row>
        </Col>
        <Col>
          <div>
            <div css={Style.title}>
              <EditableTextField value={contractName} onChange={setContractName} />
            </div>
            <div>
              <Droppable droppableId="droppable" type="top">
                {(provided, snapshot) => (
                  <div {...provided.droppableProps} ref={provided.innerRef} style={getListStyle(snapshot.isDraggingOver)}>
                    {elements.map((element, index) => (
                      <Draggable key={element.id} draggableId={element.id} index={index}>
                        {(provided, snapshot) => (
                          <div ref={provided.innerRef} {...provided.draggableProps} style={provided.draggableProps.style}>
                            <PresentationElementView
                              key={index}
                              element={element}
                              onElementChange={(element) => {
                                refreshElements(elements.set(index, element));
                              }}
                              dragHandleProps={provided.dragHandleProps}
                              onElementRemove={() => refreshElements(elements.remove(index))}
                            />
                          </div>
                        )}
                      </Draggable>
                    ))}
                    {provided.placeholder}
                  </div>
                )}
              </Droppable>
              <AddButton
                onClick={() =>
                  refreshElements(elements.push(new DocumentEditorSectionElement(nextElementId.next().toString(), "", List())))
                }
              />
            </div>
          </div>
        </Col>
      </Row>
    </DragDropContext>
  );
};

export default ContractPage;
