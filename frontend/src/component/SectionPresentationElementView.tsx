/** @jsxImportSource @emotion/react */
import React from "react";
import PresentationElementView from "./PresentationElementView";
import { List } from "immutable";
import { css } from "@emotion/react";
import { Draggable, DraggableProvidedDragHandleProps, Droppable } from "react-beautiful-dnd";
import { DocumentEditorElement, DocumentEditorSectionElement } from "../page/ContractPage";
import { Trash } from "react-bootstrap-icons";

const Styles = {
  title: css`
    text-align: center;
    font-size: 16px;
    font-weight: bold;
    padding-top: 20px;
    padding-bottom: 10px;
  `,
  removeIcon: css`
    visibility: hidden;

    div:hover > div > & {
      visibility: visible;
    }

    &:hover {
      color: red;
    }
  `,
};

interface SectionPresentationElementViewProps {
  element: DocumentEditorSectionElement;
  onNestedElementsChange: (nestedElements: List<DocumentEditorElement>) => void;
  onRemove: () => void;
  dragHandleProps: DraggableProvidedDragHandleProps | undefined;
}

const getListStyle = (isDraggingOver: boolean) => ({
  background: isDraggingOver ? "lightblue" : "white",
  minHeight: "1px",
});

const SectionPresentationElementView = ({
  element,
  onNestedElementsChange,
  onRemove,
  dragHandleProps,
}: SectionPresentationElementViewProps) => {
  return (
    <div style={{ backgroundColor: "white" }}>
      <div style={{ display: "flex" }} {...dragHandleProps}>
        <div style={{ flex: 1 }} />
        <div css={Styles.title}>{element.title}</div>
        <div style={{ flex: 1, paddingTop: "18px", paddingLeft: "15px", fontSize: "16px" }}>
          <span css={Styles.removeIcon} onClick={() => onRemove()}>
            <Trash />
          </span>
        </div>
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
                        onElementRemove={() => onNestedElementsChange(element.documentEditorElements.remove(index))}
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
