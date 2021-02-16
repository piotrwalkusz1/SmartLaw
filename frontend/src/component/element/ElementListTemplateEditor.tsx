import Template, { TemplateType } from "../../model/Template";
import { Accordion, Card } from "react-bootstrap";
import React from "react";
import ElementListStaticTemplateEditor from "./ElementListStaticTemplateEditor";
import StaticTemplate from "../../model/StaticTemplate";

interface ElementListTemplateEditorProps {
  template: Template;
  onTemplateChange: (template: Template) => void;
}

const ElementListTemplateEditor = ({ template, onTemplateChange }: ElementListTemplateEditorProps) => {
  const renderContent = () => {
    switch (template.templateType) {
      case TemplateType.Static:
        return <ElementListStaticTemplateEditor template={template as StaticTemplate} onTemplateChange={onTemplateChange} />;
      default:
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
