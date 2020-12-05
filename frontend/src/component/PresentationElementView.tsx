import React from "react";
import PresentationElement from "../model/PresentationElement";
import IndentationPresentationElementView from "./IndentationPresentationElementView";
import IndentationPresentationElement from "../model/IndentationPresentationElement";
import RuleInvocationView from "./RuleInvocationView";
import RuleInvocationPresentationElement from "../model/RuleInvocationPresentationElement";
import NaturalLanguageDocumentObject, { NaturalLanguageDocumentObjectType } from "../model/NaturalLanguageDocumentObject";
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
      if (naturalLanguageDocumentObject.type !== NaturalLanguageDocumentObjectType.Section) {
        return (
          <div>
            Expected type {NaturalLanguageDocumentObjectType.Section} but was {naturalLanguageDocumentObject.type}
          </div>
        );
      }
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
      if (naturalLanguageDocumentObject.type !== NaturalLanguageDocumentObjectType.Provision) {
        return (
          <div>
            Expected type {NaturalLanguageDocumentObjectType.Provision} but was {naturalLanguageDocumentObject.type}
          </div>
        );
      }
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
