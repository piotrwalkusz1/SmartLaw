import ListTemplate, { isListTemplate } from "../../../model/ListTemplate";
import Template from "../../../model/Template";
import ListEditor from "../../../common/ListEditor";
import TemplateEditor from "../../template/TemplateEditor";
import { List } from "immutable";
import { TemplateType } from "../../../model/TemplateType";

interface ListTemplateEditorProps<T> {
  template: Template<List<T>>;
  onChange: (template: ListTemplate<Template<T>, T>) => void;
  allowedTemplateTypes: List<TemplateType>;
  emptyItem: () => Template<T>;
}

const ListTemplateEditor = <T,>({ template, onChange, allowedTemplateTypes, emptyItem }: ListTemplateEditorProps<T>) => {
  if (isListTemplate(template)) {
    return (
      <ListEditor
        items={template.items}
        onItemsChange={(items) => onChange({ ...template, items })}
        header={(_, index) => (index + 1).toString()}
        content={(item, onItemChange, index) => (
          <TemplateEditor
            template={item}
            onTemplateChange={(item) => onChange({ ...template, items: template.items.set(index, item) })}
            allowedTemplateTypes={allowedTemplateTypes}
          />
        )}
        emptyItem={emptyItem}
        allowRemove={true}
      />
    );
  } else {
    return (
      <div>
        Expected {TemplateType.ListTemplate} but was {template.templateType}
      </div>
    );
  }
};

export default ListTemplateEditor;
