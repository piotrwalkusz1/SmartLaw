import Template from "../../model/Template";
import React from "react";
import { Accordion, Card } from "react-bootstrap";
import StringTemplateEditor from "../element/template/StringTemplateEditor";

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
              <StringTemplateEditor template={template} onChange={onTemplateChange} />
            </div>
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleContentEditor;
