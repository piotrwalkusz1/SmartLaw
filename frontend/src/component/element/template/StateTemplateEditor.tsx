import StateTemplate from "../../../model/StateTemplate";
import IdTemplateEditor from "./IdTemplateEditor";
import StringTemplateEditor from "./StringTemplateEditor";
import TypeTemplateEditor from "./TypeTemplateEditor";
import MetaValueTemplateEditor from "./MetaValueTemplateEditor";

interface StateTemplateEditorProps {
  template: StateTemplate;
  onChange: (stateTemplate: StateTemplate) => void;
}

const StateTemplateEditor = ({ template, onChange }: StateTemplateEditorProps) => {
  return (
    <div>
      <IdTemplateEditor label="Id" template={template.id} onChange={(id) => onChange({ ...template, id })} />
      <StringTemplateEditor label="Name" template={template.name} onChange={(name) => onChange({ ...template, name })} />
      <StringTemplateEditor
        label="Description"
        template={template.description}
        onChange={(description) => onChange({ ...template, description })}
      />
      <TypeTemplateEditor label="Type" template={template.type} onChange={(type) => onChange({ ...template, type })} />
      <MetaValueTemplateEditor
        label="Default value"
        template={template.defaultValue}
        onChange={(defaultValue) => onChange({ ...template, defaultValue })}
      />
    </div>
  );
};

export default StateTemplateEditor;
