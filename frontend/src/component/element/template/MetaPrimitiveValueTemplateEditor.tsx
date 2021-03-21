import StringTemplateEditor from "./StringTemplateEditor";
import { MetaPrimitiveValueTemplate } from "../../../model/MetaPrimitiveValue";

interface MetaPrimitiveValueTemplateEditorProps {
  label: string;
  template: MetaPrimitiveValueTemplate;
  onChange: (template: MetaPrimitiveValueTemplate) => void;
}

const MetaPrimitiveValueTemplateEditor = ({ label, template, onChange }: MetaPrimitiveValueTemplateEditorProps) => {
  return <StringTemplateEditor label={label} template={template.value} onChange={(value) => onChange({ ...template, value })} />;
};

export default MetaPrimitiveValueTemplateEditor;
