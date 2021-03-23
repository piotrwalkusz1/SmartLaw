import ListTemplate, { isListTemplate } from "../../../model/ListTemplate";
import Template from "../../../model/Template";
import ListEditor from "../../../common/ListEditor";
import { List } from "immutable";
import { TemplateType } from "../../../model/TemplateType";
import TemplateEditor from "../../template/TemplateEditor";
import { getCreateTemplateByMetaData, MetaData } from "../../../utils/Reflection";
import ExpandableArea from "../../../common/ExpandableArea";
import React from "react";

interface ListTemplateEditorProps<T> {
  template: Template<List<T>>;
  onChange: (template: ListTemplate<Template<T>, T>) => void;
  metaData: MetaData<T>;
  header?: string;
}

const ListTemplateEditor = <T,>({ template, onChange, metaData, header }: ListTemplateEditorProps<T>) => {
  if (isListTemplate(template)) {
    return (
      <ExpandableArea header={header}>
        <ListEditor
          items={template.items}
          onItemsChange={(items) => onChange({ ...template, items })}
          header={(_, index) => (index + 1).toString()}
          content={(item, onItemChange, index) => (
            <TemplateEditor
              template={item}
              onTemplateChange={(item) => onChange({ ...template, items: template.items.set(index, item) })}
              metaData={metaData}
            />
          )}
          emptyItem={getCreateTemplateByMetaData(metaData)}
          allowRemove={true}
        />
      </ExpandableArea>
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
