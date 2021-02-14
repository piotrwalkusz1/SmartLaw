import TemplateEditor from "../template/TemplateEditor";
import Template, { TemplateType } from "../../model/Template";
import { List } from "immutable";
import { Accordion, Card } from "react-bootstrap";
import React from "react";

interface ElementListEditorProps {
  template: Template;
  onTemplateChange: (template: Template) => void;
}

const ElementListEditor = ({ template, onTemplateChange }: ElementListEditorProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Elements
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <TemplateEditor
              template={template}
              onTemplateChange={onTemplateChange}
              allowedTemplateTypes={List([TemplateType.Static, TemplateType.Groovy])}
            />
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default ElementListEditor;
