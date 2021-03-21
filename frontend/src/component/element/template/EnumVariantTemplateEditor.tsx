import StringTemplateEditor from "./StringTemplateEditor";
import { EnumVariantTemplate } from "../../../model/EnumVariant";

interface EnumVariantTemplateEditorProps {
  template: EnumVariantTemplate;
  onChange: (template: EnumVariantTemplate) => void;
}

const EnumVariantTemplateEditor = ({ template, onChange }: EnumVariantTemplateEditorProps) => {
  return (
    <div>
      <StringTemplateEditor label="Name" template={template.name} onChange={(name) => onChange({ ...template, name })} />
      <StringTemplateEditor
        label="Description"
        template={template.description}
        onChange={(description) => onChange({ ...template, description })}
      />
    </div>
  );
};

export default EnumVariantTemplateEditor;
