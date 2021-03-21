import Template from "../../../model/Template";
import MetaValue from "../../../model/MetaValue";
import MetaPrimitiveValueTemplateEditor from "./MetaPrimitiveValueTemplateEditor";
import { MetaPrimitiveValueUtils } from "../../../model/MetaPrimitiveValue";

interface MetaValueTemplateEditorProps {
  label: string;
  template: Template<MetaValue>;
  onChange: (template: Template<MetaValue>) => void;
}

const MetaValueTemplateEditor = ({ label, template, onChange }: MetaValueTemplateEditorProps) => {
  if (MetaPrimitiveValueUtils.isTemplate(template)) {
    return <MetaPrimitiveValueTemplateEditor label={label} template={template} onChange={onChange} />;
  } else {
    return <div>Template type {template.templateType} is not supported</div>;
  }
};

export default MetaValueTemplateEditor;
