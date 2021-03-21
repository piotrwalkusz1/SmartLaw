import React from "react";
import Template, { prepareEmptyTemplate } from "../../model/Template";
import TextEngineTemplateEditor from "./TextEngineTemplateEditor";
import StaticTemplateEditor from "./StaticTemplateEditor";
import { isStaticTemplate } from "../../model/StaticTemplate";
import GroovyTemplateEditor from "./GroovyTemplateEditor";
import { List } from "immutable";
import SelectField from "../../common/SelectField";
import StateTemplateEditor from "../element/template/StateTemplateEditor";
import EnumDefinitionTemplateEditor from "../element/template/EnumDefinitionTemplateEditor";
import EnumVariantTemplateEditor from "../element/template/EnumVariantTemplateEditor";
import { StateElementUtils } from "../../model/StateElement";
import { EnumDefinitionElementUtils } from "../../model/EnumDefinitionElement";
import { EnumVariantUtils } from "../../model/EnumVariant";
import { TemplateType } from "../../model/TemplateType";
import { isTextEngineTemplate } from "../../model/TextEngineTemplate";
import { isGroovyTemplate } from "../../model/GroovyTemplate";

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
    } else if (StateElementUtils.isTemplate(template)) {
      return <StateTemplateEditor template={template} onChange={onTemplateChange} />;
    } else if (EnumDefinitionElementUtils.isTemplate(template)) {
      return <EnumDefinitionTemplateEditor template={template} onChange={onTemplateChange} />;
    } else if (EnumVariantUtils.isTemplate(template)) {
      return <EnumVariantTemplateEditor template={template} onChange={onTemplateChange} />;
    } else {
      return <div>Template type "{template.templateType}" is not supported.</div>;
    }
  };

  return (
    <div>
      {allowedTemplateTypes.size <= 1 ? (
        <> </>
      ) : (
        <SelectField
          label="Template type"
          value={template.templateType}
          onChange={(type) => onTemplateChange(prepareEmptyTemplate(type))}
          options={allowedTemplateTypes}
          display={(type) => type}
        />
      )}
      {renderTemplate(template, onTemplateChange)}
    </div>
  );
};

export default TemplateEditor;
