/** @jsxImportSource @emotion/react */
import React, { useEffect, useState } from "react";
import Contract from "../model/Contract";
import * as DocumentService from "../service/DocumentService";
import PresentationElementView from "../component/PresentationElementView";
import { DocumentEditorContext } from "../context/DocumentEditorContext";
import { convertDocumentToNaturalLanguage } from "../service/ProjectService";
import NaturalLanguageDocument from "../model/NaturalLanguageDocument";
import NaturalLanguageDocumentObject from "../model/NaturalLanguageDocumentObject";
import { Col, Container, Row } from "react-bootstrap";
import { css } from "@emotion/react";
import { DragDropContext } from "react-beautiful-dnd";

const Style = {
  title: css`
    text-align: center;
    font-size: 20px;
    font-weight: bold;
    padding: 10px 0;
  `,
};

const ContractPage = () => {
  const contractId = "2"; //TODO
  const projectId = "1";
  const [contract, setContract] = useState<Contract | null>(null);
  const [naturalLanguageDocument, setNaturalLanguageDocument] = useState<NaturalLanguageDocument | null>(null);
  useEffect(() => {
    DocumentService.getDocument<Contract>(contractId).then(setContract);
  }, []);
  useEffect(() => {
    if (contract !== null) {
      convertDocumentToNaturalLanguage(projectId, contract).then(setNaturalLanguageDocument);
    }
  }, [contract]);

  return contract === null || naturalLanguageDocument === null ? (
    <div />
  ) : (
    <Container>
      <Row>
        <Col>
          <div css={Style.title}>{naturalLanguageDocument.title}</div>
          <DocumentEditorContext.Provider value={{ projectId: projectId, documentId: contractId }}>
            {contract.presentationElements.map((presentationElement, index) => (
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
            ))}
          </DocumentEditorContext.Provider>
        </Col>
      </Row>
    </Container>
  );
};

export default ContractPage;
