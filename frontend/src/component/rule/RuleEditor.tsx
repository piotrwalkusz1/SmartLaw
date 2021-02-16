import { Form } from "react-bootstrap";
import React from "react";
import Rule from "../../model/Rule";
import TextField from "../../common/TextField";
import RuleArgumentListEditor from "./RuleArgumentListEditor";
import ElementListTemplateEditor from "../element/ElementListTemplateEditor";
import RuleContentEditor from "./RuleContentEditor";
import IdField from "../IdField";

interface RuleEditorProps {
  rule: Rule;
  onRuleChange: (rule: Rule) => void;
}

const RuleEditor = ({ rule, onRuleChange }: RuleEditorProps) => {
  return (
    <Form>
      <IdField label="Id" value={rule.id} onValueChange={(id) => onRuleChange({ ...rule, id })} />
      <TextField label="Name" value={rule.name} onChange={(value) => onRuleChange({ ...rule, name: value })} />
      <TextField label="Description" value={rule.description || ""} onChange={(value) => onRuleChange({ ...rule, description: value })} />
      <RuleArgumentListEditor
        ruleArguments={rule.arguments}
        onRuleArgumentsChange={(ruleArguments) => onRuleChange({ ...rule, arguments: ruleArguments })}
      />
      <RuleContentEditor template={rule.content} onTemplateChange={(content) => onRuleChange({ ...rule, content })} />
      <ElementListTemplateEditor template={rule.elements} onTemplateChange={(elements) => onRuleChange({ ...rule, elements })} />
    </Form>
  );
};

export default RuleEditor;
