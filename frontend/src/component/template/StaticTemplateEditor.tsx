import StaticTemplate from "../../model/StaticTemplate";
import { Form } from "react-bootstrap";
import React from "react";

interface StaticTemplateEditorProps {
  template: StaticTemplate;
  onTemplateChange: (template: StaticTemplate) => void;
}

const StaticTemplateEditor = ({ template, onTemplateChange }: StaticTemplateEditorProps) => {
  return (
    <Form.Group>
      <Form.Label>Content</Form.Label>
      <Form.Control
        type="text"
        value={template.value.toString()}
        onChange={(event) => onTemplateChange({ ...template, value: event.target.value })}
      />
    </Form.Group>
  );
};

export default StaticTemplateEditor;
