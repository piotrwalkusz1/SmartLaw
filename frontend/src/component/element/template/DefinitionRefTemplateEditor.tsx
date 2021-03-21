import IdTemplateEditor from "./IdTemplateEditor";
import { DefinitionRefTemplate } from "../../../model/DefinitionRef";

interface DefinitionRefTemplateEditorProps {
  label: string;
  template: DefinitionRefTemplate;
  onChange: (template: DefinitionRefTemplate) => void;
}

const DefinitionRefTemplateEditor = ({ label, template, onChange }: DefinitionRefTemplateEditorProps) => {
  return <IdTemplateEditor template={template.definition} onChange={(definition) => onChange({ ...template, definition })} label={label} />;
};

export default DefinitionRefTemplateEditor;
