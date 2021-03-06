import Template from "../../model/Template";
import { Accordion, Card } from "react-bootstrap";
import React from "react";
import ElementListStaticTemplateEditor from "./ElementListStaticTemplateEditor";
import { isStaticTemplate } from "../../model/StaticTemplate";
import { List } from "immutable";
import Element from "../../model/Element";

interface ElementListTemplateEditorProps {
  template: Template<List<Element>>;
  onTemplateChange: (template: Template<List<Element>>) => void;
}

const ElementListTemplateEditor = ({ template, onTemplateChange }: ElementListTemplateEditorProps) => {
  const renderContent = () => {
    if (isStaticTemplate(template)) {
      return <ElementListStaticTemplateEditor template={template} onTemplateChange={onTemplateChange} />;
    } else {
      return <div>Template type {template.templateType} is not supported</div>;
    }
  };

  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Elements
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>{renderContent()}</Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default ElementListTemplateEditor;
