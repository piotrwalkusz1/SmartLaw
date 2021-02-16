import { RULE_ARGUMENT_TYPES } from "../../service/Types";
import React from "react";
import MetaArgument from "../../model/MetaArgument";
import TextField from "../../common/TextField";
import SelectField from "../../common/SelectField";
import { areIdsEqual } from "../../model/Id";
import { List } from "immutable";

interface RuleArgumentEditorProps {
  ruleArgument: MetaArgument;
  onRuleArgumentChange: (ruleArgument: MetaArgument) => void;
}

const RuleArgumentEditor = ({ ruleArgument, onRuleArgumentChange }: RuleArgumentEditorProps) => {
  return (
    <div>
      <TextField label="Name" value={ruleArgument.name} onChange={(value) => onRuleArgumentChange({ ...ruleArgument, name: value })} />
      <SelectField
        label="Type"
        value={RULE_ARGUMENT_TYPES.find((type) => areIdsEqual(type, ruleArgument.type)) || null}
        onChange={(value) => onRuleArgumentChange({ ...ruleArgument, type: value })}
        options={List(RULE_ARGUMENT_TYPES)}
        display={(option) => option.id}
      />
    </div>
  );
};

export default RuleArgumentEditor;
