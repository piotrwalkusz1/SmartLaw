import React from "react";
import Template, { prepareEmptyTemplate } from "../../model/Template";
import { getDerivativeMetaData } from "../../utils/ModelUtils";
import SelectField from "../../common/SelectField";
import { List } from "immutable";
import { ComplexMetaData, MetaData } from "../../utils/Reflection";
import { TemplateType } from "../../model/TemplateType";
import ExpandableArea from "../../common/ExpandableArea";

interface TemplateEditorProps<T> {
  template: Template<T>;
  onTemplateChange: (template: Template<T>) => void;
  metaData: MetaData<T>;
  fieldName?: string;
}

const transformFieldName = (fieldName: string): string => {
  return (
    fieldName
      // insert a space before all caps
      .replace(/([A-Z])/g, " $1")
      .toLocaleLowerCase()
      // uppercase the first character
      .replace(/^./, function (str) {
        return str.toUpperCase();
      })
  );
};

const TemplateEditor = <T,>({ template, onTemplateChange, metaData, fieldName }: TemplateEditorProps<T>) => {
  const derivativeMetaDataList = getDerivativeMetaData(metaData);
  let derivativeMetaData: MetaData<T> | undefined = undefined;
  if (derivativeMetaDataList.length > 1) {
    derivativeMetaData = derivativeMetaDataList.find((derivativeMetaData) => derivativeMetaData.templateType === template.templateType);
  } else if (derivativeMetaDataList.length === 1) {
    derivativeMetaData = derivativeMetaDataList[0];
  }

  const renderTemplateTypeSelect = () => {
    if (derivativeMetaDataList.length > 1) {
      return (
        <SelectField
          label="Template type"
          value={template.templateType}
          onChange={(type) => onTemplateChange(prepareEmptyTemplate(type))}
          options={List(derivativeMetaDataList.map((derivativeMetaData) => derivativeMetaData.templateType as TemplateType))}
          display={(type) => type}
        />
      );
    } else {
      return <></>;
    }
  };

  if (derivativeMetaData) {
    if (derivativeMetaData.renderTemplateEditor) {
      return (
        <div>
          {renderTemplateTypeSelect()}
          {derivativeMetaData.renderTemplateEditor(template, onTemplateChange, fieldName)}
        </div>
      );
    } else {
      const complexMetaData = derivativeMetaData as ComplexMetaData<T>;
      if (complexMetaData.fields) {
        return (
          <ExpandableArea header={fieldName}>
            <div>
              {renderTemplateTypeSelect()}
              {Object.entries<MetaData<any>>(complexMetaData.fields)
                .filter(([, fieldMetaData]) => !fieldMetaData.excludeFromTemplate)
                .map(([fieldName, fieldMetaData]) => {
                  const fieldTemplate: Template<any> | undefined = (template as any)[fieldName];
                  if (!fieldTemplate) {
                    return (
                      <div key={fieldName}>
                        Template {template.templateType} has no field {fieldName}
                      </div>
                    );
                  }
                  return (
                    <div key={fieldName}>
                      <TemplateEditor
                        template={fieldTemplate}
                        onTemplateChange={(fieldTemplate) =>
                          onTemplateChange({
                            ...template,
                            [fieldName]: fieldTemplate,
                          })
                        }
                        fieldName={transformFieldName(fieldName)}
                        metaData={fieldMetaData}
                      />
                    </div>
                  );
                })}
            </div>
          </ExpandableArea>
        );
      } else {
        return <div>ComplexMetaData was expected. Field name: {fieldName}</div>;
      }
    }
  } else if (template.templateType) {
    return <div>Unsupported template type: {template.templateType}</div>;
  } else {
    return <div>No template type</div>;
  }
};

export default TemplateEditor;
