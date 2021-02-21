import React from "react";
import MetaArgument from "../../model/MetaArgument";
import TextField from "../../common/TextField";
import SelectField from "../../common/SelectField";
import Id, { areIdsEqual } from "../../model/Id";
import { List } from "immutable";
import { Button } from "react-bootstrap";

interface RuleArgumentEditorProps {
  ruleArgument: MetaArgument;
  onRuleArgumentChange: (ruleArgument: MetaArgument) => void;
  ruleArgumentTypes: List<Id>;
}

const RuleArgumentEditor = ({ ruleArgument, onRuleArgumentChange, ruleArgumentTypes }: RuleArgumentEditorProps) => {
  return (
    <div>
      <TextField label="Name" value={ruleArgument.name} onChange={(value) => onRuleArgumentChange({ ...ruleArgument, name: value })} />
      <SelectField
        label="Type"
        value={ruleArgumentTypes.find((type) => areIdsEqual(type, ruleArgument.type)) || null}
        onChange={(value) => onRuleArgumentChange({ ...ruleArgument, type: value })}
        options={ruleArgumentTypes}
        display={(option) => option.id}
      />
    </div>
  );
};

export default RuleArgumentEditor;
