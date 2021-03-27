import React from "react";
import Template from "../../model/Template";
import { getDerivativeMetaDataAndTemplateTypes } from "../../utils/ModelUtils";
import { ComplexMetaData, MetaData } from "../../utils/Reflection";
import ExpandableArea from "../../common/ExpandableArea";
import TemplateTypeSelector from "./TemplateTypeSelector";

interface TemplateEditorProps<T> {
  template: Template<T>;
  onTemplateChange: (template: Template<T>) => void;
  metaData: MetaData<T>;
  fieldName?: string;
  hideTemplateTypeSelector?: boolean;
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

const TemplateEditor = <T,>({ template, onTemplateChange, metaData, fieldName, hideTemplateTypeSelector }: TemplateEditorProps<T>) => {
  const { derivativeMetaData, templateTypes } = getDerivativeMetaDataAndTemplateTypes(template.templateType, metaData);

  const renderTemplateTypeSelect = (label?: string) => {
    if (hideTemplateTypeSelector) {
      return <></>;
    }

    return (
      <TemplateTypeSelector
        templateType={template.templateType}
        templateTypes={templateTypes}
        onTemplateChange={onTemplateChange}
        label={label}
        inline={label !== undefined}
      />
    );
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
          <ExpandableArea header={fieldName && renderTemplateTypeSelect(fieldName)}>
            <div>
              {!fieldName && renderTemplateTypeSelect()}
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
