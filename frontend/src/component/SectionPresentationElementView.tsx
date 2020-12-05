/** @jsxImportSource @emotion/react */
import React from "react";
import PresentationElementView from "./PresentationElementView";
import { List } from "immutable";
import { css } from "@emotion/react";
import { Draggable, Droppable } from "react-beautiful-dnd";
import { DocumentEditorElement, DocumentEditorSectionElement } from "../page/ContractPage";

const Styles = {
  title: css`
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    padding-top: 20px;
    padding-bottom: 10px;
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

interface SectionPresentationElementViewProps {
  element: DocumentEditorSectionElement;
  onNestedElementsChange: (nestedElements: List<DocumentEditorElement>) => void;
}

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

const SectionPresentationElementView = ({ element, onNestedElementsChange }: SectionPresentationElementViewProps) => {
  return (
    <div>
      <div css={Styles.title}>{element.title}</div>
      <div>
        <Droppable droppableId={element.id} type="nested">
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef} style={getListStyle(snapshot.isDraggingOver)}>
              {element.documentEditorElements.map((documentEditorElement, index) => (
                <Draggable key={documentEditorElement.id} draggableId={documentEditorElement.id} index={index}>
                  {(provided, snapshot) => (
                    <div
                      ref={provided.innerRef}
                      {...provided.draggableProps}
                      style={getItemStyle(snapshot.isDragging, provided.draggableProps.style)}
                    >
                      <div style={{ display: "flex", padding: "10px" }}>
                        <div css={Styles.holder} {...provided.dragHandleProps}>
                          {documentEditorElement.id}
                        </div>
                        <div css={Styles.nestedElement}>
                          <PresentationElementView
                            element={documentEditorElement}
                            onElementChange={(nestedElement) => {
                              onNestedElementsChange(element.documentEditorElements.set(index, nestedElement));
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
      </div>
    </div>
  );
};

export default SectionPresentationElementView;
