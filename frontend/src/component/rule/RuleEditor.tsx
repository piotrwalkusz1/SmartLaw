import { Form } from "react-bootstrap";
import React from "react";
import Rule from "../../model/Rule";
import TextField from "../../common/TextField";
import RuleArgumentListEditor from "./RuleArgumentListEditor";
import ElementListTemplateEditor from "../element/template/ElementListTemplateEditor";
import RuleContentEditor from "./RuleContentEditor";
import IdField from "../IdField";
import ImplementedRuleInterfacesSelector from "./ImplementedRuleInterfacesSelector";
import { List } from "immutable";
import Id from "../../model/Id";

interface RuleEditorProps {
  projectId: string;
  rule: Rule;
  onRuleChange: (rule: Rule) => void;
  ruleArgumentTypes: List<Id>;
}

const RuleEditor = ({ projectId, rule, onRuleChange, ruleArgumentTypes }: RuleEditorProps) => {
  return (
    <Form>
      <IdField label="Id" value={rule.id} onValueChange={(id) => onRuleChange({ ...rule, id })} />
      <TextField label="Name" value={rule.name} onChange={(value) => onRuleChange({ ...rule, name: value })} />
      <TextField label="Description" value={rule.description || ""} onChange={(value) => onRuleChange({ ...rule, description: value })} />
      <RuleArgumentListEditor
        ruleArguments={rule.arguments}
        onRuleArgumentsChange={(ruleArguments) => onRuleChange({ ...rule, arguments: ruleArguments })}
        ruleArgumentTypes={ruleArgumentTypes}
      />
      <RuleContentEditor template={rule.content} onTemplateChange={(content) => onRuleChange({ ...rule, content })} />
      <ElementListTemplateEditor template={rule.elements} onTemplateChange={(elements) => onRuleChange({ ...rule, elements })} />
      <ImplementedRuleInterfacesSelector
        projectId={projectId}
        rulesInterfaces={rule.interfaces}
        onRulesInterfacesChange={(interfaces) => onRuleChange({ ...rule, interfaces })}
      />
    </Form>
  );
};

export default RuleEditor;
