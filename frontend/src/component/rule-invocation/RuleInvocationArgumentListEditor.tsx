import MetaArgument from "../../model/MetaArgument";
import { List, Map } from "immutable";
import MetaValue from "../../model/MetaValue";
import { ValidationResult } from "../../model/ValidationResult";
import RuleInvocationArgumentEditor from "./RuleInvocationArgumentEditor";
import { prepareEmptyRuleInvocationArgument } from "../../service/RuleService";
import React from "react";

interface RuleInvocationArgumentListEditorProps {
  ruleArguments: List<MetaArgument>;
  ruleInvocationArguments: Map<string, MetaValue>;
  onRuleInvocationArgumentsChange: (ruleInvocationArguments: Map<string, MetaValue>) => void;
  validationResults: Map<string, List<ValidationResult>>;
}

const RuleInvocationArgumentListEditor = ({
  ruleInvocationArguments,
  onRuleInvocationArgumentsChange,
  ruleArguments,
  validationResults,
}: RuleInvocationArgumentListEditorProps) => {
  return (
    <div>
      {ruleArguments.map((ruleArgument, index) => (
        <div key={index}>
          <RuleInvocationArgumentEditor
            ruleArgument={ruleArgument}
            ruleInvocationArgument={ruleInvocationArguments.get(ruleArgument.name) || prepareEmptyRuleInvocationArgument(ruleArgument)}
            onRuleInvocationArgumentChange={(argument) =>
              onRuleInvocationArgumentsChange(ruleInvocationArguments.set(ruleArgument.name, argument))
            }
            validationResults={validationResults.get(ruleArgument.name) || List()}
          />
        </div>
      ))}
    </div>
  );
};

export default RuleInvocationArgumentListEditor;
