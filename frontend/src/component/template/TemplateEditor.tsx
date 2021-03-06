import React from "react";
import Template, { TemplateType } from "../../model/Template";
import TextEngineTemplateEditor from "./TextEngineTemplateEditor";
import TextEngineTemplate, { isTextEngineTemplate, TextEngineType } from "../../model/TextEngineTemplate";
import StaticTemplateEditor from "./StaticTemplateEditor";
import StaticTemplate, { isStaticTemplate } from "../../model/StaticTemplate";
import GroovyTemplateEditor from "./GroovyTemplateEditor";
import GroovyTemplate, { isGroovyTemplate } from "../../model/GroovyTemplate";
import { List } from "immutable";
import SelectField from "../../common/SelectField";

interface TemplateEditorProps {
  template: Template<any>;
  onTemplateChange: (template: Template<any>) => void;
  allowedTemplateTypes: List<TemplateType>;
}

const TemplateEditor = ({ template, onTemplateChange, allowedTemplateTypes }: TemplateEditorProps) => {
  const renderTemplate = (template: Template<any>, onTemplateChange: (template: Template<any>) => void) => {
    if (isTextEngineTemplate(template)) {
      return <TextEngineTemplateEditor template={template} onTemplateChange={onTemplateChange} />;
    } else if (isStaticTemplate(template)) {
      return <StaticTemplateEditor template={template} onTemplateChange={onTemplateChange} />;
    } else if (isGroovyTemplate(template)) {
      return <GroovyTemplateEditor template={template} onTemplateChange={onTemplateChange} />;
    } else {
      return <div>Template type "{template.templateType}" is not supported.</div>;
    }
  };

  const prepareEmptyTemplate = (templateType: TemplateType): Template<any> => {
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
        } as StaticTemplate<any>;
      case TemplateType.Groovy:
        return {
          templateType: TemplateType.Groovy,
          script: "",
        } as GroovyTemplate<any>;
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
