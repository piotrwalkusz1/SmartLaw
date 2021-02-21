import React from "react";
import MetaArgument from "../../model/MetaArgument";
import MetaValue from "../../model/MetaValue";
import RuleInvocationArgumentValueEditor from "./RuleInvocationArgumentValueEditor";
import { List } from "immutable";
import { ValidationResult } from "../../model/ValidationResult";
import RuleInvocationValidationResults from "./RuleInvocationValidationResults";
import ExpandableArea from "../../common/ExpandableArea";

interface RuleInvocationArgumentEditorProps {
  ruleArgument: MetaArgument;
  ruleInvocationArgument: MetaValue;
  onRuleInvocationArgumentChange: (ruleInvocationArgument: MetaValue) => void;
  validationResults: List<ValidationResult>;
}

const RuleInvocationArgumentEditor = ({
  ruleArgument,
  ruleInvocationArgument,
  onRuleInvocationArgumentChange,
  validationResults,
}: RuleInvocationArgumentEditorProps) => {
  const isAnyValidationError = (): boolean => {
    return !!validationResults.find((validationResult) => validationResult.error !== null);
  };

  return (
    <ExpandableArea header={ruleArgument.displayName || ruleArgument.name} errorBorder={isAnyValidationError()}>
      <div>
        <RuleInvocationArgumentValueEditor
          ruleInvocationArgument={ruleInvocationArgument}
          onRuleInvocationArgumentChange={onRuleInvocationArgumentChange}
          ruleArgument={ruleArgument}
        />
        <RuleInvocationValidationResults validationResults={validationResults} />
      </div>
    </ExpandableArea>
  );
};

export default RuleInvocationArgumentEditor;
