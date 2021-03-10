import Template, { TemplateType } from "../../../model/Template";
import { Accordion, Card } from "react-bootstrap";
import React from "react";
import { List } from "immutable";
import Element from "../../../model/Element";
import { isListTemplate } from "../../../model/ListTemplate";
import ListTemplateEditor from "./ListTemplateEditor";

interface ElementListTemplateEditorProps {
  template: Template<List<Element>>;
  onTemplateChange: (template: Template<List<Element>>) => void;
}

const ElementListTemplateEditor = ({ template, onTemplateChange }: ElementListTemplateEditorProps) => {
  const renderContent = () => {
    if (isListTemplate(template)) {
      return (
        <ListTemplateEditor template={template} onChange={onTemplateChange} allowedTemplateTypes={List([TemplateType.StateTemplate])} />
      );
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
