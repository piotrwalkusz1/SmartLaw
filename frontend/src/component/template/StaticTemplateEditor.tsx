import StaticTemplate from "../../model/StaticTemplate";
import { Form } from "react-bootstrap";
import React from "react";

interface StaticTemplateEditorProps {
  template: StaticTemplate<any>;
  onTemplateChange: (template: StaticTemplate<any>) => void;
}

const StaticTemplateEditor = <T,>({ template, onTemplateChange }: StaticTemplateEditorProps) => {
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
