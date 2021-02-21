import React from "react";
import MetaArgument from "../../model/MetaArgument";
import MetaValue from "../../model/MetaValue";
import RuleInvocationArgumentValueEditor from "./RuleInvocationArgumentValueEditor";
import { List } from "immutable";
import { ValidationResult } from "../../model/ValidationResult";
import RuleInvocationValidationResults from "./RuleInvocationValidationResults";

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
  return (
    <div>
      <span>{ruleArgument.displayName || ruleArgument.name}</span>
      <RuleInvocationArgumentValueEditor
        ruleInvocationArgument={ruleInvocationArgument}
        onRuleInvocationArgumentChange={onRuleInvocationArgumentChange}
        ruleArgument={ruleArgument}
      />
      <RuleInvocationValidationResults validationResults={validationResults} />
    </div>
  );
};

export default RuleInvocationArgumentEditor;
