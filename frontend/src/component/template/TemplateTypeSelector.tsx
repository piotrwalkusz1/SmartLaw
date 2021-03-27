/** @jsxImportSource @emotion/react */
import SelectField from "../../common/SelectField";
import Template, { prepareEmptyTemplate } from "../../model/Template";
import { List } from "immutable";
import { TemplateType } from "../../model/TemplateType";
import React from "react";
import { css } from "@emotion/react";

const Styles = {
  inlineTemplateSelector: css`
    .form-group {
      margin-bottom: 0;
    }
  `,
  templateSelector: css`
    .form-control {
      max-width: 250px;
    }
  `,
};

interface TemplateTypeSelectorProps<T> {
  templateType: TemplateType;
  templateTypes: List<TemplateType>;
  onTemplateChange: (template: Template<T>) => void;
  label?: string;
  inline?: boolean;
}

const TemplateTypeSelector = <T,>({ templateType, templateTypes, onTemplateChange, label, inline }: TemplateTypeSelectorProps<T>) => {
  if (templateTypes.size > 1) {
    return (
      <div css={inline ? [Styles.inlineTemplateSelector, Styles.templateSelector] : Styles.templateSelector}>
        <SelectField
          label={label || "Template type"}
          inlineLabel={inline}
          value={templateType}
          onChange={(type) => onTemplateChange(prepareEmptyTemplate(type))}
          options={templateTypes}
          display={(type) => type}
        />
      </div>
    );
  } else {
    return <div>{label}</div>;
  }
};

export default TemplateTypeSelector;
