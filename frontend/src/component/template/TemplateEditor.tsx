import React from "react";
import Template, { prepareEmptyTemplate, TemplateType } from "../../model/Template";
import TextEngineTemplateEditor from "./TextEngineTemplateEditor";
import { isTextEngineTemplate } from "../../model/TextEngineTemplate";
import StaticTemplateEditor from "./StaticTemplateEditor";
import { isStaticTemplate } from "../../model/StaticTemplate";
import GroovyTemplateEditor from "./GroovyTemplateEditor";
import { isGroovyTemplate } from "../../model/GroovyTemplate";
import { List } from "immutable";
import SelectField from "../../common/SelectField";
import StateTemplateEditor from "../element/template/StateTemplateEditor";
import { isStateTemplate } from "../../model/StateTemplate";

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
    } else if (isStateTemplate(template)) {
      return <StateTemplateEditor template={template} onChange={onTemplateChange} />;
    } else {
      return <div>Template type "{template.templateType}" is not supported.</div>;
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
