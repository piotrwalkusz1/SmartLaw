import TemplateEditor from "../template/TemplateEditor";
import { List } from "immutable";
import Template, { TemplateType } from "../../model/Template";
import React from "react";
import { Accordion, Card } from "react-bootstrap";

interface RuleContentEditorProps {
  template: Template<string>;
  onTemplateChange: (template: Template<string>) => void;
}

const RuleContentEditor = ({ template, onTemplateChange }: RuleContentEditorProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Content
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <div style={{ paddingLeft: "15px" }}>
              <TemplateEditor
                template={template}
                onTemplateChange={onTemplateChange}
                allowedTemplateTypes={List([TemplateType.Static, TemplateType.TextEngine])}
              />
            </div>
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleContentEditor;
