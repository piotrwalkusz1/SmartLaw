import { decodeEnum, decodeString } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import Id, { decodeId, prepareEmptyId } from "./Id";
import { prepareEmptyIdTemplate } from "./IdTemplate";

export default interface EnumValue {
  enumDefinition: Id;
  value: string;
}

export const decodeEnumValue = (json: any): EnumValue => {
  return {
    enumDefinition: decodeId(json.enumDefinition),
    value: decodeString(json.value),
  };
};

export const prepareEmptyEnumValue = (): EnumValue => {
  return {
    enumDefinition: prepareEmptyId(),
    value: "",
  };
};

export interface EnumValueTemplate extends WrapWithTemplate<EnumValue> {}

export const decodeEnumValueTemplate = (json: any): EnumValueTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    enumDefinition: decodeTemplate(json.enumDefinition, decodeId),
    value: decodeTemplate(json.value, decodeString),
  };
};

export const prepareEmptyEnumValueTemplate = (): EnumValueTemplate => {
  return {
    templateType: TemplateType.EnumValueTemplate,
    enumDefinition: prepareEmptyIdTemplate(),
    value: prepareStaticTemplate(""),
  };
};

export const isEnumValueTemplate = <T>(template: Template<T>): template is EnumValueTemplate => {
  return template.templateType === TemplateType.EnumValueTemplate;
};
