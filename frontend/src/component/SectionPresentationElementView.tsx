/** @jsxImportSource @emotion/react */
import React from "react";
import PresentationElementView from "./PresentationElementView";
import { List } from "immutable";
import { css } from "@emotion/react";
import { Draggable, DraggableProvidedDragHandleProps, Droppable } from "react-beautiful-dnd";
import { DocumentEditorElement, DocumentEditorSectionElement } from "../page/ContractPage";

const Styles = {
  title: css`
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    padding-top: 20px;
    padding-bottom: 10px;
  `,
};

interface SectionPresentationElementViewProps {
  element: DocumentEditorSectionElement;
  onNestedElementsChange: (nestedElements: List<DocumentEditorElement>) => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const getListStyle = (isDraggingOver: boolean) => ({
  background: isDraggingOver ? "lightblue" : "lightgrey",
});

const SectionPresentationElementView = ({ element, onNestedElementsChange, dragHandleProps }: SectionPresentationElementViewProps) => {
  return (
    <div style={{ backgroundColor: "white" }}>
      <div css={Styles.title} {...dragHandleProps}>
        {element.title}
      </div>
      <div>
        <Droppable droppableId={element.id} type="nested">
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef} style={getListStyle(snapshot.isDraggingOver)}>
              {element.documentEditorElements.map((documentEditorElement, index) => (
                <Draggable key={documentEditorElement.id} draggableId={documentEditorElement.id} index={index}>
                  {(provided, snapshot) => (
                    <div ref={provided.innerRef} {...provided.draggableProps} style={provided.draggableProps.style}>
                      <PresentationElementView
                        element={documentEditorElement}
                        onElementChange={(nestedElement) => {
                          onNestedElementsChange(element.documentEditorElements.set(index, nestedElement));
                        }}
                        dragHandleProps={provided.dragHandleProps}
                      />
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
