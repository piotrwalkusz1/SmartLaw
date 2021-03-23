import IdTemplateEditor from "./IdTemplateEditor";
import StringTemplateEditor from "./StringTemplateEditor";
import TypeTemplateEditor from "./TypeTemplateEditor";
import MetaValueTemplateEditor from "./MetaValueTemplateEditor";
import { FunctionElementTemplate } from "../../../model/FunctionElement";
import ListTemplateEditor from "./ListTemplateEditor";
import { List } from "immutable";
import { TemplateType } from "../../../model/TemplateType";
import { FunctionArgumentDefinitionUtils } from "../../../model/FunctionArgumentDefinition";

interface FunctionTemplateEditorProps {
  template: FunctionElementTemplate;
  onChange: (template: FunctionElementTemplate) => void;
}

const FunctionTemplateEditor = ({ template, onChange }: FunctionTemplateEditorProps) => {
  return (
    <div>
      <IdTemplateEditor label="Id" template={template.id} onChange={(id) => onChange({ ...template, id })} />
      <StringTemplateEditor label="Name" template={template.name} onChange={(name) => onChange({ ...template, name })} />
      <StringTemplateEditor
        label="Description"
        template={template.description}
        onChange={(description) => onChange({ ...template, description })}
      />
      // TODO
      {/*<ListTemplateEditor*/}
      {/*  template={template.arguments}*/}
      {/*  onChange={(functionArguments) => onChange({ ...template, arguments: functionArguments })}*/}
      {/*  allowedTemplateTypes={List([TemplateType.FunctionArgumentDefinitionTemplate])}*/}
      {/*  emptyItem={FunctionArgumentDefinitionUtils.createTemplate}*/}
      {/*/>*/}
      {/*<ListTemplateEditor*/}
      {/*  template={template.body}*/}
      {/*  onChange={(body) => onChange({ ...template, body })}*/}
      {/*  allowedTemplateTypes={List([TemplateType.FunctionArgumentDefinitionTemplate])}*/}
      {/*  emptyItem={FunctionArgumentDefinitionUtils.createTemplate}*/}
      {/*/>*/}
    </div>
  );
};

export default FunctionTemplateEditor;
