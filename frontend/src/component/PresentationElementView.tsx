import React from "react";
import SectionPresentationElementView from "./SectionPresentationElementView";
import RuleInvocationView from "./RuleInvocationView";
import { DocumentEditorElement, DocumentEditorRuleInvocationElement, DocumentEditorSectionElement } from "../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";

interface PresentationElementViewProps {
  element: DocumentEditorElement;
  onElementChange: (element: DocumentEditorElement) => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const PresentationElementView = ({ element, onElementChange, dragHandleProps }: PresentationElementViewProps) => {
  if (element instanceof DocumentEditorSectionElement) {
    return (
      <SectionPresentationElementView
        element={element}
        onNestedElementsChange={(nestedElements) =>
          onElementChange(new DocumentEditorSectionElement(element.id, element.title, nestedElements))
        }
        dragHandleProps={dragHandleProps}
      />
    );
  } else if (element instanceof DocumentEditorRuleInvocationElement) {
    return (
      <RuleInvocationView
        element={element}
        onArgumentsChange={(newArguments) => onElementChange(element.withRuleInvocationArguments(newArguments))}
        dragHandleProps={dragHandleProps}
      />
    );
  } else {
    throw Error("DocumentEditorElement type is not supported");
  }
};

export default PresentationElementView;
