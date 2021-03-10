import { decodeString } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import MetaPrimitiveValue from "./MetaPrimitiveValue";
import { prepareStaticTemplate } from "./StaticTemplate";

export const decodeMetaPrimitiveValueTemplate = (json: any): MetaPrimitiveValueTemplate => {
  return {
    templateType: TemplateType.MetaPrimitiveValueTemplate,
    value: decodeTemplate(json.value, decodeString),
  };
};

export default interface MetaPrimitiveValueTemplate extends Template<MetaPrimitiveValue> {
  value: Template<string>;
}

export const isMetaPrimitiveValueTemplate = (template: Template<MetaPrimitiveValue>): template is MetaPrimitiveValueTemplate => {
  return template.templateType === TemplateType.MetaPrimitiveValueTemplate;
};

export const prepareEmptyMetaPrimitiveValueTemplate = (): MetaPrimitiveValueTemplate => {
  return {
    templateType: TemplateType.MetaPrimitiveValueTemplate,
    value: prepareStaticTemplate(""),
  };
};
