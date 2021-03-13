import ListTemplate from "../../../model/ListTemplate";
import Template, { TemplateType } from "../../../model/Template";
import ListEditor from "../../../common/ListEditor";
import TemplateEditor from "../../template/TemplateEditor";
import { List } from "immutable";

interface ListTemplateEditorProps<T> {
  template: ListTemplate<Template<T>, T>;
  onChange: (template: ListTemplate<Template<T>, T>) => void;
  allowedTemplateTypes: List<TemplateType>;
  emptyItem: () => Template<T>;
}

const ListTemplateEditor = <T,>({ template, onChange, allowedTemplateTypes, emptyItem }: ListTemplateEditorProps<T>) => {
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
};

export default ListTemplateEditor;
