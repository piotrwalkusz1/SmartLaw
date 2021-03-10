import ListTemplate from "../../../model/ListTemplate";
import Template, { TemplateType } from "../../../model/Template";
import ListEditor from "../../../common/ListEditor";
import TemplateEditor from "../../template/TemplateEditor";
import { List } from "immutable";
import { prepareEmptyStateTemplate } from "../../../model/StateTemplate";

interface ListTemplateEditorProps<T> {
  template: ListTemplate<Template<T>, T>;
  onChange: (template: ListTemplate<Template<T>, T>) => void;
  allowedTemplateTypes: List<TemplateType>;
}

const ListTemplateEditor = <T,>({ template, onChange, allowedTemplateTypes }: ListTemplateEditorProps<T>) => {
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
      emptyItem={prepareEmptyStateTemplate}
      allowRemove={true}
    />
  );
};

export default ListTemplateEditor;
