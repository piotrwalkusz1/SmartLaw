import Template from "../../../model/Template";
import MetaValue from "../../../model/MetaValue";
import { isMetaPrimitiveValueTemplate } from "../../../model/MetaPrimitiveValueTemplate";
import MetaPrimitiveValueTemplateEditor from "./MetaPrimitiveValueTemplateEditor";

interface MetaValueTemplateEditorProps {
  label: string;
  template: Template<MetaValue>;
  onChange: (template: Template<MetaValue>) => void;
}

const MetaValueTemplateEditor = ({ label, template, onChange }: MetaValueTemplateEditorProps) => {
  if (isMetaPrimitiveValueTemplate(template)) {
    return <MetaPrimitiveValueTemplateEditor label={label} template={template} onChange={onChange} />;
  } else {
    return <div>Template type {template.templateType} is not supported</div>;
  }
};

export default MetaValueTemplateEditor;
