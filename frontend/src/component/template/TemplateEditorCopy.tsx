import React from "react";
import Template, { prepareEmptyTemplate } from "../../model/Template";
import { getDerivativeModelsUtils, ModelUtils } from "../../utils/ModelUtils";
import SelectField from "../../common/SelectField";
import { List } from "immutable";

interface TemplateEditorProps<T extends Template<R>, R> {
  template: Template<R>;
  onTemplateChange: (template: T) => void;
  modelUtils: ModelUtils<T, R>;
}

const TemplateEditor = <T extends Template<R>, R>({ template, onTemplateChange, modelUtils }: TemplateEditorProps<T, R>) => {
  const modelsUtils = getDerivativeModelsUtils(modelUtils);
  const renderTemplateTypeSelect = () => {
    if (modelsUtils.length > 1) {
      <SelectField
        label="Template type"
        value={template.templateType}
        onChange={(type) => onTemplateChange(prepareEmptyTemplate(type) as T)}
        options={List(modelsUtils.map((modelUtils) => modelUtils.templateType))}
        display={(type) => type}
      />;
    } else {
      return <></>;
    }
  };

  <div>{renderTemplate(template, onTemplateChange)}</div>;
};

export default TemplateEditor;
