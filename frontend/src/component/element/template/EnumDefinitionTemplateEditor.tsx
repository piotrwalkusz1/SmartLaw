import EnumDefinitionTemplate from "../../../model/EnumDefinitionTemplate";
import IdTemplateEditor from "./IdTemplateEditor";
import StringTemplateEditor from "./StringTemplateEditor";
import ListTemplateEditor from "./ListTemplateEditor";
import { List } from "immutable";
import { TemplateType } from "../../../model/Template";
import { isListTemplate } from "../../../model/ListTemplate";
import { prepareEmptyEnumVariantTemplate } from "../../../model/EnumVariantTemplate";
import { Form } from "react-bootstrap";

interface EnumDefinitionTemplateEditorProps {
  template: EnumDefinitionTemplate;
  onChange: (template: EnumDefinitionTemplate) => void;
}

const EnumDefinitionTemplateEditor = ({ template, onChange }: EnumDefinitionTemplateEditorProps) => {
  const renderList = () => {
    if (isListTemplate(template.variants)) {
      return (
        <div>
          <Form.Label>Variants</Form.Label>
          <ListTemplateEditor
            template={template.variants}
            onChange={(variants) => onChange({ ...template, variants })}
            allowedTemplateTypes={List([TemplateType.EnumVariantTemplate])}
            emptyItem={prepareEmptyEnumVariantTemplate}
          />
        </div>
      );
    } else {
      return <div>Template type {template.variants.templateType} is not supported</div>;
    }
  };

  return (
    <div>
      <IdTemplateEditor label="Id" template={template.id} onChange={(id) => onChange({ ...template, id })} />
      <StringTemplateEditor label="Name" template={template.name} onChange={(name) => onChange({ ...template, name })} />
      <StringTemplateEditor
        label="Description"
        template={template.description}
        onChange={(description) => onChange({ ...template, description })}
      />
      {renderList()}
    </div>
  );
};

export default EnumDefinitionTemplateEditor;
