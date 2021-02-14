import React from "react";
import Template, { TemplateType } from "../../model/Template";
import TextEngineTemplateEditor from "./TextEngineTemplateEditor";
import TextEngineTemplate, { TextEngineType } from "../../model/TextEngineTemplate";
import StaticTemplateEditor from "./StaticTemplateEditor";
import StaticTemplate from "../../model/StaticTemplate";
import GroovyTemplateEditor from "./GroovyTemplateEditor";
import GroovyTemplate from "../../model/GroovyTemplate";
import { List } from "immutable";
import SelectField from "../../common/SelectField";

interface TemplateEditorProps {
  template: Template;
  onTemplateChange: (template: Template) => void;
  allowedTemplateTypes: List<TemplateType>;
}

const TemplateEditor = ({ template, onTemplateChange, allowedTemplateTypes }: TemplateEditorProps) => {
  const renderTemplate = (template: Template, onTemplateChange: (template: Template) => void) => {
    switch (template.templateType) {
      case TemplateType.TextEngine:
        return <TextEngineTemplateEditor template={template as TextEngineTemplate} onTemplateChange={onTemplateChange} />;
      case TemplateType.Static:
        return <StaticTemplateEditor template={template as StaticTemplate} onTemplateChange={onTemplateChange} />;
      case TemplateType.Groovy:
        return <GroovyTemplateEditor template={template as GroovyTemplate} onTemplateChange={onTemplateChange} />;
      default:
        return <div>Template type "{template.templateType}" is not supported.</div>;
    }
  };

  const prepareEmptyTemplate = (templateType: TemplateType): Template => {
    switch (templateType) {
      case TemplateType.TextEngine:
        return {
          templateType: TemplateType.TextEngine,
          type: TextEngineType.FreeMarker,
          template: "",
        } as TextEngineTemplate;
      case TemplateType.Static:
        return {
          templateType: TemplateType.Static,
          value: "",
        } as StaticTemplate;
      case TemplateType.Groovy:
        return {
          templateType: TemplateType.Groovy,
          script: "",
        } as GroovyTemplate;
    }
  };

  return (
    <div>
      <SelectField
        label="Template type"
        value={template.templateType}
        onChange={(type) => onTemplateChange(prepareEmptyTemplate(type))}
        options={allowedTemplateTypes}
        display={(type) => type}
      />
      {renderTemplate(template, onTemplateChange)}
    </div>
  );
};

export default TemplateEditor;
