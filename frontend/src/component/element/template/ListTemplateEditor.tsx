import ListTemplate, { isListTemplate } from "../../../model/ListTemplate";
import Template from "../../../model/Template";
import ListEditor from "../../../common/ListEditor";
import { List } from "immutable";
import { TemplateType } from "../../../model/TemplateType";
import { getCreateTemplateByMetaData, MetaData } from "../../../utils/Reflection";
import ExpandableArea from "../../../common/ExpandableArea";
import React from "react";
import { getTemplateTypesFromMetaData } from "../../../utils/ModelUtils";
import TemplateTypeSelector from "../../template/TemplateTypeSelector";
import TemplateEditor from "../../template/TemplateEditor";

interface ListTemplateEditorProps<T> {
  template: Template<List<T>>;
  onChange: (template: ListTemplate<Template<T>, T>) => void;
  metaData: MetaData<T>;
  header?: string;
}

const ListTemplateEditor = <T,>({ template, onChange, metaData, header }: ListTemplateEditorProps<T>) => {
  if (isListTemplate(template)) {
    const onItemTemplateChange = (itemTemplate: Template<T>, index: number) => {
      onChange({ ...template, items: template.items.set(index, itemTemplate) });
    };

    const renderTemplateTypeSelector = (itemTemplateType: TemplateType, index: number) => {
      const templateTypes = getTemplateTypesFromMetaData(metaData);

      return (
        <TemplateTypeSelector
          templateType={itemTemplateType}
          templateTypes={templateTypes}
          onTemplateChange={(itemTemplate) => onItemTemplateChange(itemTemplate, index)}
          label={(index + 1).toString()}
          inline={true}
        />
      );
    };

    return (
      <ExpandableArea header={header}>
        <ListEditor
          items={template.items}
          onItemsChange={(items) => onChange({ ...template, items })}
          header={(itemTemplate, index) => {
            return renderTemplateTypeSelector(itemTemplate.templateType, index);
          }}
          content={(item, onItemChange, index) => (
            <TemplateEditor
              template={item}
              onTemplateChange={(item) => onItemTemplateChange(item, index)}
              metaData={metaData}
              hideTemplateTypeSelector={true}
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
