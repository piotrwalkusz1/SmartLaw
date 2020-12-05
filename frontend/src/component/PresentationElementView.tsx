import React from "react";
import SectionPresentationElementView from "./SectionPresentationElementView";
import RuleInvocationView from "./RuleInvocationView";
import { DocumentEditorElement, DocumentEditorRuleInvocationElement, DocumentEditorSectionElement } from "../page/ContractPage";

interface PresentationElementViewProps {
  element: DocumentEditorElement;
  onElementChange: (element: DocumentEditorElement) => void;
}

const PresentationElementView = ({ element, onElementChange }: PresentationElementViewProps) => {
  if (element instanceof DocumentEditorSectionElement) {
    return (
      <SectionPresentationElementView
        element={element}
        onNestedElementsChange={(nestedElements) =>
          onElementChange(new DocumentEditorSectionElement(element.id, element.title, nestedElements))
        }
      />
    );
  } else if (element instanceof DocumentEditorRuleInvocationElement) {
    return (
      <RuleInvocationView
        element={element}
        onArgumentsChange={(newArguments) => onElementChange(element.withRuleInvocationArguments(newArguments))}
      />
    );
  } else {
    throw Error("DocumentEditorElement type is not supported");
  }
};

export default PresentationElementView;
