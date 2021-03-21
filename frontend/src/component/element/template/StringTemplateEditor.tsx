import Template from "../../../model/Template";
import StaticTemplate, { isStaticTemplate, prepareStaticTemplate } from "../../../model/StaticTemplate";
import TextField from "../../../common/TextField";
import SelectField from "../../../common/SelectField";
import { List } from "immutable";
import { Form } from "react-bootstrap";
import React from "react";
import TextEngineTemplate, { isTextEngineTemplate, prepareTextEngineTemplate, TextEngineType } from "../../../model/TextEngineTemplate";
import { TemplateType } from "../../../model/TemplateType";

interface StringTemplateEditorProps {
  label?: string;
  placeholder?: string;
  template: Template<string>;
  onChange: (template: Template<string>) => void;
}

const STRING_TEMPLATE_TYPES: List<string> = List([TemplateType.Static, ...Object.values(TextEngineType)]);

const StringTemplateEditor = ({ template, onChange, label, placeholder }: StringTemplateEditorProps) => {
  const changeStaticTemplate = (staticTemplate: StaticTemplate<string>, newValue: string): StaticTemplate<string> => {
    return { ...staticTemplate, value: newValue };
  };

  const changeTextEngineTemplate = (textEngineTemplate: TextEngineTemplate, newTemplate: string): TextEngineTemplate => {
    return { ...textEngineTemplate, template: newTemplate };
  };

  const renderTextField = () => {
    if (isStaticTemplate(template)) {
      return (
        <TextField
          placeholder={placeholder}
          value={template.value || ""}
          onChange={(value) => onChange(changeStaticTemplate(template, value))}
        />
      );
    } else if (isTextEngineTemplate(template)) {
      return (
        <TextField
          placeholder={placeholder}
          value={template.template || ""}
          onChange={(value) => onChange(changeTextEngineTemplate(template, value))}
        />
      );
    } else {
      return <div>Template type {template.templateType} is not supported</div>;
    }
  };

  const changeTemplateType = (stringTemplateType: string) => {
    if (stringTemplateType === TemplateType.Static) {
      if (isTextEngineTemplate(template)) {
        onChange(prepareStaticTemplate(template.template));
      }
    } else {
      if (isStaticTemplate(template)) {
        onChange(prepareTextEngineTemplate(stringTemplateType as TextEngineType, template.value));
      }
    }
  };

  return (
    <div>
      {label && <Form.Label>{label}</Form.Label>}
      <div style={{ display: "flex" }}>
        <div style={{ flexGrow: 1 }}>{renderTextField()}</div>
        <SelectField
          value={isTextEngineTemplate(template) ? template.type : TemplateType.Static}
          onChange={changeTemplateType}
          options={STRING_TEMPLATE_TYPES}
          display={(type) => type}
        />
      </div>
    </div>
  );
};

export default StringTemplateEditor;
