import TextField from "../../common/TextField";
import GroovyTemplate from "../../model/GroovyTemplate";

interface GroovyTemplateEditorProps {
  template: GroovyTemplate<any>;
  onTemplateChange: (template: GroovyTemplate<any>) => void;
}

const GroovyTemplateEditor = ({ template, onTemplateChange }: GroovyTemplateEditorProps) => {
  return (
    <div>
      <TextField label="Script" value={template.script} onChange={(script) => onTemplateChange({ ...template, script })} />
    </div>
  );
};

export default GroovyTemplateEditor;
