/** @jsxImportSource @emotion/react */
import React from "react";
import MetaValue from "../../model/MetaValue";
import { Map } from "immutable";
import { DocumentEditorRuleInvocationElement } from "../../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";
import RuleInvocationPresentationView from "../rule-invocation/RuleInvocationPresentationView";

interface RuleInvocationViewProps {
  element: DocumentEditorRuleInvocationElement;
  onArgumentsChange: (newArguments: Map<string, MetaValue>) => void;
  onRemove: () => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationView = ({ element, onArgumentsChange, onRemove, dragHandleProps }: RuleInvocationViewProps) => {
  return (
    <RuleInvocationPresentationView
      ruleContent={element.extendedPresentationElement.naturalLanguageDocumentObject.content}
      validationResults={Map(element.extendedPresentationElement.validationResults)}
      ruleInvocation={element.extendedPresentationElement.presentationElement.ruleInvocation}
      onRuleInvocationChange={(ruleInvocation) => onArgumentsChange(ruleInvocation.arguments)}
      rule={element.extendedPresentationElement.rule}
      onRemove={onRemove}
      dragHandleProps={dragHandleProps}
      elementValidationErrors={element.extendedPresentationElement.elementValidationErrors}
    />
  );
};
export default RuleInvocationView;
