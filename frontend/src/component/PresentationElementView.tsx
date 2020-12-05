import React from "react";
import PresentationElement from "../model/PresentationElement";
import IndentationPresentationElementView from "./IndentationPresentationElementView";
import IndentationPresentationElement from "../model/IndentationPresentationElement";
import RuleInvocationView from "./RuleInvocationView";
import RuleInvocationPresentationElement from "../model/RuleInvocationPresentationElement";
import NaturalLanguageDocumentObject from "../model/NaturalLanguageDocumentObject";
import NaturalLanguageSection from "../model/NaturalLanguageSection";
import NaturalLanguageProvision from "../model/NaturalLanguageProvision";

interface PresentationElementViewProps {
  presentationElement: PresentationElement;
  onPresentationElementChange: (value: PresentationElement) => void;
  naturalLanguageDocumentObject: NaturalLanguageDocumentObject;
}

const PresentationElementView = ({
  presentationElement,
  onPresentationElementChange,
  naturalLanguageDocumentObject,
}: PresentationElementViewProps) => {
  switch (presentationElement.type) {
    case "Indentation":
      const indentationPresentationElement = presentationElement as IndentationPresentationElement;
      return (
        <IndentationPresentationElementView
          presentationElement={indentationPresentationElement}
          onNestedPresentationElementsChange={(nestedPresentationElements) =>
            onPresentationElementChange({
              ...indentationPresentationElement,
              presentationElements: nestedPresentationElements,
            } as IndentationPresentationElement)
          }
          naturalLanguageSection={naturalLanguageDocumentObject as NaturalLanguageSection}
        />
      );
    case "RuleInvocation":
      const ruleInvocationPresentationElement = presentationElement as RuleInvocationPresentationElement;
      return (
        <RuleInvocationView
          ruleInvocation={ruleInvocationPresentationElement.ruleInvocation}
          onArgumentsChange={(newArguments) =>
            onPresentationElementChange({
              ...ruleInvocationPresentationElement,
              ruleInvocation: {
                ...ruleInvocationPresentationElement.ruleInvocation,
                arguments: newArguments,
              },
            } as RuleInvocationPresentationElement)
          }
          naturalLanguageProvision={naturalLanguageDocumentObject as NaturalLanguageProvision}
        />
      );
    default:
      return <div>Presentation element with type {presentationElement.type} is not supported</div>;
  }
};

export default PresentationElementView;
