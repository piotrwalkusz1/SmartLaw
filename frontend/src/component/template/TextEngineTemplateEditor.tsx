import { Form } from "react-bootstrap";
import React from "react";
import TextEngineTemplate from "../../model/TextEngineTemplate";

interface TextEngineTemplateEditorProps {
  template: TextEngineTemplate;
  onTemplateChange: (template: TextEngineTemplate) => void;
}

const TextEngineTemplateEditor = ({ template, onTemplateChange }: TextEngineTemplateEditorProps) => {
  return (
    <Form.Group>
      <Form.Label>Content</Form.Label>
      <Form.Control
        type="text"
        value={template.template.toString()}
        onChange={(event) => onTemplateChange({ ...template, template: event.target.value })}
      />
    </Form.Group>
  );
};

export default TextEngineTemplateEditor;
