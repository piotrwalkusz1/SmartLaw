import Template from "../../model/Template";
import ListEditor from "../../common/ListEditor";
import { TemplateType } from "../../model/TemplateType";
import { createTemplateByMetaData, MetaData } from "../../utils/Reflection";
import ExpandableArea from "../../common/ExpandableArea";
import React from "react";
import { getTemplateTypesFromMetaData } from "../../utils/ModelUtils";
import TemplateTypeSelector from "./TemplateTypeSelector";
import TemplateEditor from "./TemplateEditor";
import MapTemplate, { isMapTemplate } from "../../model/MapTemplate";
import { List, Map } from "immutable";
import TextField from "../../common/TextField";

interface MapTemplateEditorProps<T> {
  template: Template<Map<string, T>>;
  onChange: (template: MapTemplate<T>) => void;
  metaData: MetaData<T>;
  header?: string;
}

const MapTemplateEditor = <T,>({ template, onChange, metaData, header }: MapTemplateEditorProps<T>) => {
  if (isMapTemplate(template)) {
    const itemsAsList = List(template.items.entries()).map(([key, value]) => ({ key: key, template: value }));

    const onItemTemplateChange = (itemTemplate: Template<T>, oldKey: string, newKey: string) => {
      const newItems =
        oldKey === newKey ? template.items.set(newKey, itemTemplate) : template.items.set(newKey, itemTemplate).remove(oldKey);
      onChange({ ...template, items: newItems });
    };

    const renderTemplateTypeSelector = (itemTemplateType: TemplateType, key: string) => {
      const templateTypes = getTemplateTypesFromMetaData(metaData);

      return (
        <TemplateTypeSelector
          templateType={itemTemplateType}
          templateTypes={templateTypes}
          onTemplateChange={(itemTemplate) => onItemTemplateChange(itemTemplate, key, key)}
          label={key}
          inline={true}
        />
      );
    };

    const prepareEmptyTemplate = (metaData: MetaData<T>): Template<T> => {
      const emptyTemplate = createTemplateByMetaData(metaData);
      if (emptyTemplate) {
        return emptyTemplate;
      } else {
        throw Error("Cannot create empty template");
      }
    };

    return (
      <ExpandableArea header={header}>
        <ListEditor
          items={itemsAsList}
          onItemsChange={(items) => onChange({ ...template, items: Map(items.map((item) => [item.key, item.template])) })}
          header={(itemTemplate, index) => {
            return renderTemplateTypeSelector(itemTemplate.template.templateType, itemTemplate.key);
          }}
          content={(item, onItemChange, index) => (
            <div>
              <TextField value={item.key} onChange={(key) => onItemTemplateChange(item.template, item.key, key)} />
              <TemplateEditor
                template={item.template}
                onTemplateChange={(template) => onItemTemplateChange(template, item.key, item.key)}
                metaData={metaData}
                hideTemplateTypeSelector={true}
              />
            </div>
          )}
          emptyItem={() => ({ key: "", template: prepareEmptyTemplate(metaData) })}
          allowRemove={true}
        />
      </ExpandableArea>
    );
  } else {
    return (
      <div>
        Expected {TemplateType.MapTemplate} but was {template.templateType}
      </div>
    );
  }
};

export default MapTemplateEditor;
