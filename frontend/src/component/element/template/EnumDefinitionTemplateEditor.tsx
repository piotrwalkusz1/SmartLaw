import IdTemplateEditor from "./IdTemplateEditor";
import StringTemplateEditor from "./StringTemplateEditor";
import ListTemplateEditor from "./ListTemplateEditor";
import { List } from "immutable";
import { Form } from "react-bootstrap";
import { EnumDefinitionElementTemplate } from "../../../model/EnumDefinitionElement";
import { EnumVariantUtils } from "../../../model/EnumVariant";
import { TemplateType } from "../../../model/TemplateType";

interface EnumDefinitionTemplateEditorProps {
  template: EnumDefinitionElementTemplate;
  onChange: (template: EnumDefinitionElementTemplate) => void;
}

const EnumDefinitionTemplateEditor = ({ template, onChange }: EnumDefinitionTemplateEditorProps) => {
  return (
    <div>
      <IdTemplateEditor label="Id" template={template.id} onChange={(id) => onChange({ ...template, id })} />
      <StringTemplateEditor label="Name" template={template.name} onChange={(name) => onChange({ ...template, name })} />
      <StringTemplateEditor
        label="Description"
        template={template.description}
        onChange={(description) => onChange({ ...template, description })}
      />
      <div>
        <Form.Label>Variants</Form.Label>
        <ListTemplateEditor
          template={template.variants}
          onChange={(variants) => onChange({ ...template, variants })}
          allowedTemplateTypes={List([TemplateType.EnumVariantTemplate])}
          emptyItem={EnumVariantUtils.createTemplate}
        />
      </div>
    </div>
  );
};

export default EnumDefinitionTemplateEditor;
