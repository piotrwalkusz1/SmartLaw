/** @jsxImportSource @emotion/react */
import React, {useEffect, useState} from "react";
import Contract from "../model/Contract";
import * as DocumentService from "../service/DocumentService";
import PresentationElementView from "../component/PresentationElementView";
import {DocumentEditorContext} from "../context/DocumentEditorContext";
import {convertDocumentToNaturalLanguage} from "../service/ProjectService";
import NaturalLanguageDocumentObject from "../model/NaturalLanguageDocumentObject";
import {Col, Container, Row} from "react-bootstrap";
import {css} from "@emotion/react";
import {DragDropContext, Draggable, Droppable} from "react-beautiful-dnd";
import {moveItemInList} from "../utils/ListUtils";
import {List} from "immutable";
import NaturalLanguageProvision from "../model/NaturalLanguageProvision";
import RuleInvocation from "../model/RuleInvocation";

const Style = {
  title: css`
    text-align: center;
    font-size: 20px;
    font-weight: bold;
    padding: 10px 0;
  `,
};

interface DocumentEditorElement {
}

class DocumentEditorSectionElement implements DocumentEditorElement {

}

class DocumentEditorRuleInvocationElement implements DocumentEditorElement {
  id: string;
  ruleInvocation: RuleInvocation;
}

const convertNaturalLanguageDocumentObjectToDocumentEditorElement =
  (naturalLanguageDocumentObject: NaturalLanguageDocumentObject): DocumentEditorElement => {
    if (naturalLanguageDocumentObject instanceof NaturalLanguageProvision) {
      return
    }
  }

const ContractPage = () => {
  const contractId = "2";
  const projectId = "1";
  const [contract, setContract] = useState<Contract | null>(null);
  const [elements, setElements] = useState<List<DocumentEditorElement> | null>(null);
  useEffect(() => {
    DocumentService.getDocument<Contract>(contractId).then(setContract);
  }, []);
  useEffect(() => {
    if (contract !== null) {
      convertDocumentToNaturalLanguage(projectId, contract).then(naturalLanguageDocument => {
        naturalLanguageDocument.
      });
    }
  }, [contract]);

  const getListStyle = (isDraggingOver: boolean) => ({
    background: isDraggingOver ? "lightblue" : "lightgrey",
  });

  const onDragEnd = (result: any) => {
    if (contract === null || naturalLanguageDocument === null || !result.destination) {
      return;
    }

    setContract({
      ...contract,
      presentationElements: moveItemInList(contract.presentationElements, result.source.index, result.destination.index),
    });
    setNaturalLanguageDocument({
      ...naturalLanguageDocument,
      objects: moveItemInList(naturalLanguageDocument.objects, result.source.index, result.destination.index),
    });
  };

  return contract === null || naturalLanguageDocument === null ? (
    <div/>
  ) : (
    <Container>
      <Row>
        <Col>
          <div css={Style.title}>{naturalLanguageDocument.title}</div>
          <DocumentEditorContext.Provider value={{projectId: projectId, documentId: contractId}}>
            <DragDropContext onDragEnd={onDragEnd}>
              <Droppable droppableId="0">
                {(provided, snapshot) => (
                  <div {...provided.droppableProps} ref={provided.innerRef}
                       style={getListStyle(snapshot.isDraggingOver)}>
                    {contract.presentationElements.map((presentationElement, index) => (
                      <Draggable key={index} draggableId={index + ""} index={index}>
                        {(provided, snapshot) => (
                          <div ref={provided.innerRef} {...provided.draggableProps}>
                            <span
                              {...provided.dragHandleProps}
                              style={{
                                display: "inline-block",
                                margin: "0 10px",
                                border: "1px solid #000",
                              }}
                            >
                              Drag
                            </span>
                            <PresentationElementView
                              key={index}
                              presentationElement={presentationElement}
                              onPresentationElementChange={(newPresentationElement) => {
                                setContract((_) => ({
                                  ...contract,
                                  presentationElements: contract.presentationElements.set(index, newPresentationElement),
                                }));
                              }}
                              naturalLanguageDocumentObject={naturalLanguageDocument.objects.get(index) as NaturalLanguageDocumentObject}
                            />
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
