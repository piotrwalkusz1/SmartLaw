import { Accordion, Card, Form } from "react-bootstrap";
import React from "react";
import Rule from "../../model/Rule";
import TextField from "../../common/TextField";
import RuleArgumentsEditor from "./RuleArgumentsEditor";
import ElementListEditor from "./ElementListEditor";
import RuleContentEditor from "./RuleContentEditor";

interface RuleEditorProps {
  rule: Rule;
  onRuleChange: (rule: Rule) => void;
}

const RuleEditor = ({ rule, onRuleChange }: RuleEditorProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          {rule.name}
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <Form>
              <TextField label="Name" value={rule.name} onChange={(value) => onRuleChange({ ...rule, name: value })} />
              <TextField
                label="Description"
                value={rule.description || ""}
                onChange={(value) => onRuleChange({ ...rule, description: value })}
              />
              <RuleArgumentsEditor
                ruleArguments={rule.arguments}
                onRuleArgumentsChange={(ruleArguments) => onRuleChange({ ...rule, arguments: ruleArguments })}
              />
              <RuleContentEditor template={rule.content} onTemplateChange={(content) => onRuleChange({ ...rule, content })} />
              <ElementListEditor template={rule.elements} onTemplateChange={(elements) => onRuleChange({ ...rule, elements })} />
            </Form>
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleEditor;
