import Template from "../../../model/Template";
import Type from "../../../model/Type";
import DefinitionRefTemplateEditor from "./DefinitionRefTemplateEditor";
import { DefinitionRefUtils } from "../../../model/DefinitionRef";

interface TypeTemplateEditorProps {
  label: string;
  template: Template<Type>;
  onChange: (template: Template<Type>) => void;
}

const TypeTemplateEditor = ({ label, template, onChange }: TypeTemplateEditorProps) => {
  if (DefinitionRefUtils.isTemplate(template)) {
    return <DefinitionRefTemplateEditor label={label} template={template} onChange={onChange} />;
  } else {
    return <div>Template type {template.templateType} is not supported</div>;
  }
};

export default TypeTemplateEditor;
