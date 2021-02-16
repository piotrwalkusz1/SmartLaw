import { Form } from "react-bootstrap";
import React from "react";
import TextField from "../../common/TextField";
import RuleInterface from "../../model/RuleInterface";
import IdField from "../IdField";

interface RuleInterfaceEditorProps {
  ruleInterface: RuleInterface;
  onRuleInterfaceChange: (ruleInterface: RuleInterface) => void;
}

const RuleInterfaceEditor = ({ ruleInterface, onRuleInterfaceChange }: RuleInterfaceEditorProps) => {
  return (
    <Form>
      <IdField label="Id" value={ruleInterface.id} onValueChange={(id) => onRuleInterfaceChange({ ...ruleInterface, id })} />
      <TextField label="Name" value={ruleInterface.name} onChange={(name) => onRuleInterfaceChange({ ...ruleInterface, name })} />
      <TextField
        label="Description"
        value={ruleInterface.description || ""}
        onChange={(description) => onRuleInterfaceChange({ ...ruleInterface, description })}
      />
    </Form>
  );
};

export default RuleInterfaceEditor;
