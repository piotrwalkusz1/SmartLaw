/** @jsxImportSource @emotion/react */
import React from "react";
import PresentationElementView from "./PresentationElementView";
import { List } from "immutable";
import PresentationElement from "../model/PresentationElement";
import { css } from "@emotion/react";
import IndentationPresentationElement from "../model/IndentationPresentationElement";
import NaturalLanguageSection from "../model/NaturalLanguageSection";
import { Draggable, Droppable } from "react-beautiful-dnd";

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
        <Droppable droppableId="1">
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef}>
              {presentationElement.presentationElements.map((nestedPresentationElement, index) => {
                const naturalLanguageDocumentObject = naturalLanguageSection.provisions.get(index);
                if (naturalLanguageDocumentObject === undefined) {
                  return <div>No natural language document object</div>;
                }
                return (
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
                          presentationElement={nestedPresentationElement}
                          onPresentationElementChange={(newNestedPresentationElement) => {
                            onNestedPresentationElementsChange(
                              presentationElement.presentationElements.set(index, newNestedPresentationElement)
                            );
                          }}
                          naturalLanguageDocumentObject={naturalLanguageDocumentObject}
                        />
                      </div>
                    )}
                  </Draggable>
                );
              })}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </div>
    </div>
  );
};

export default IndentationPresentationElementView;
