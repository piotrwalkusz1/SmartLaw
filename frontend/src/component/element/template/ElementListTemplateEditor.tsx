import Template from "../../../model/Template";
import { Accordion, Card } from "react-bootstrap";
import React from "react";
import { List } from "immutable";
import Element from "../../../model/Element";
import ListTemplateEditor from "./ListTemplateEditor";
import { isListTemplate } from "../../../model/ListTemplate";
import { StateElementUtils } from "../../../model/StateElement";
import { TemplateType } from "../../../model/TemplateType";

interface ElementListTemplateEditorProps {
  template: Template<List<Element>>;
  onTemplateChange: (template: Template<List<Element>>) => void;
}

const ElementListTemplateEditor = ({ template, onTemplateChange }: ElementListTemplateEditorProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Elements
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <ListTemplateEditor
              template={template}
              onChange={onTemplateChange}
              allowedTemplateTypes={List([TemplateType.StateTemplate, TemplateType.EnumDefinitionTemplate])}
              emptyItem={StateElementUtils.createTemplate}
            />
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default ElementListTemplateEditor;
