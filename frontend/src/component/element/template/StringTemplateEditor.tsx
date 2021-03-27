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
  allowNull?: boolean;
}

const NULL_VALUE = "Null";

const StringTemplateEditor = ({ template, onChange, label, placeholder, allowNull }: StringTemplateEditorProps) => {
  const templateTypes: List<string> = List(
    ([TemplateType.Static, ...Object.values(TextEngineType)] as Array<string>).concat(allowNull ? [NULL_VALUE] : [])
  );

  const changeStaticTemplate = (staticTemplate: StaticTemplate<string>, newValue: string): StaticTemplate<string> => {
    return { ...staticTemplate, value: newValue };
  };

  const changeTextEngineTemplate = (textEngineTemplate: TextEngineTemplate, newTemplate: string): TextEngineTemplate => {
    return { ...textEngineTemplate, template: newTemplate };
  };

  const getSelectedTemplateType = (): string => {
    if (isTextEngineTemplate(template)) {
      return template.type;
    } else if (isStaticTemplate(template)) {
      if (template.value === null) {
        return NULL_VALUE;
      } else {
        return TemplateType.Static;
      }
    } else {
      return NULL_VALUE;
    }
  };

  const renderTextField = () => {
    if (isStaticTemplate(template)) {
      if (template.value === null) {
        return <TextField placeholder={placeholder} value="" disabled={true} />;
      } else {
        return (
          <TextField
            placeholder={placeholder}
            value={template.value || ""}
            onChange={(value) => onChange(changeStaticTemplate(template, value))}
          />
        );
      }
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
      } else {
        onChange(prepareStaticTemplate(""));
      }
    } else if (stringTemplateType === NULL_VALUE) {
      onChange(prepareStaticTemplate(null));
    } else {
      if (isStaticTemplate(template)) {
        onChange(prepareTextEngineTemplate(stringTemplateType as TextEngineType, template.value || ""));
      }
    }
  };

  return (
    <div>
      {label && <Form.Label>{label}</Form.Label>}
      <div style={{ display: "flex" }}>
        <div style={{ flexGrow: 1 }}>{renderTextField()}</div>
        <SelectField value={getSelectedTemplateType()} onChange={changeTemplateType} options={templateTypes} display={(type) => type} />
      </div>
    </div>
  );
};

export default StringTemplateEditor;
