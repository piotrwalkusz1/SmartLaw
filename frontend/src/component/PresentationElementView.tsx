import React from "react";
import SectionPresentationElementView from "./SectionPresentationElementView";
import RuleInvocationView from "./RuleInvocationView";
import { DocumentEditorElement, DocumentEditorRuleInvocationElement, DocumentEditorSectionElement } from "../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";

interface PresentationElementViewProps {
  element: DocumentEditorElement;
  onElementChange: (element: DocumentEditorElement) => void;
  onElementRemove: () => void;
  dragHandleProps: DraggableProvidedDragHandleProps | undefined;
}

const PresentationElementView = ({ element, onElementChange, dragHandleProps, onElementRemove }: PresentationElementViewProps) => {
  if (element instanceof DocumentEditorSectionElement) {
    return (
      <SectionPresentationElementView
        element={element}
        onNestedElementsChange={(nestedElements) =>
          onElementChange(new DocumentEditorSectionElement(element.id, element.title, nestedElements))
        }
        dragHandleProps={dragHandleProps}
        onRemove={onElementRemove}
      />
    );
  } else if (element instanceof DocumentEditorRuleInvocationElement) {
    return (
      <RuleInvocationView
        element={element}
        onArgumentsChange={(newArguments) => onElementChange(element.withRuleInvocationArguments(newArguments))}
        dragHandleProps={dragHandleProps}
        onRemove={onElementRemove}
      />
    );
  } else {
    throw Error("DocumentEditorElement type is not supported");
  }
};

export default PresentationElementView;
