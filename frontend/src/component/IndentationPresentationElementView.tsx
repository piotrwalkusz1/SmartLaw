/** @jsxImportSource @emotion/react */
import React from "react";
import PresentationElementView from "./PresentationElementView";
import { List } from "immutable";
import PresentationElement from "../model/PresentationElement";
import NaturalLanguageDocumentObject from "../model/NaturalLanguageDocumentObject";
import { css } from "@emotion/react";
import IndentationPresentationElement from "../model/IndentationPresentationElement";
import NaturalLanguageSection from "../model/NaturalLanguageSection";

const Styles = {
  title: css`
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    padding-top: 20px;
    padding-bottom: 10px;
  `,
};

interface IndentationPresentationElementViewProps {
  presentationElement: IndentationPresentationElement;
  onNestedPresentationElementsChange: (nestedPresentationElements: List<PresentationElement>) => void;
  naturalLanguageSection: NaturalLanguageSection;
}

const IndentationPresentationElementView = ({
  presentationElement,
  onNestedPresentationElementsChange,
  naturalLanguageSection,
}: IndentationPresentationElementViewProps) => {
  return (
    <div>
      <div css={Styles.title}>{naturalLanguageSection.title}</div>
      <div>
        {presentationElement.presentationElements.map((nestedPresentationElement, index) => (
          <PresentationElementView
            key={index}
            presentationElement={nestedPresentationElement}
            onPresentationElementChange={(newNestedPresentationElement) => {
              onNestedPresentationElementsChange(presentationElement.presentationElements.set(index, newNestedPresentationElement));
            }}
            naturalLanguageDocumentObject={naturalLanguageSection.provisions.get(index) as NaturalLanguageDocumentObject}
          />
        ))}
      </div>
    </div>
  );
};

export default IndentationPresentationElementView;
