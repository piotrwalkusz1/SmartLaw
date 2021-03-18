import { decodeEnum, decodeNullable, decodeString } from "../utils/Decoders";
import { decodeDefinitionRef, prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";
import Type, { decodeType } from "./Type";

export default interface GenericParameter {
  name: string;
  baseType: Type | null;
}

export const decodeGenericParameter = (json: any): GenericParameter => {
  return {
    name: decodeString(json.name),
    baseType: decodeType(json.baseType),
  };
};

export const prepareEmptyGenericParameter = (): GenericParameter => {
  return {
    name: "",
    baseType: prepareDefinitionRef(TYPES.UINT),
  };
};

export interface GenericParameterTemplate extends WrapWithTemplate<GenericParameter> {}

export const decodeGenericParameterTemplate = (json: any): GenericParameterTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    name: decodeTemplate(json.name, decodeString),
    baseType: decodeTemplate(json.baseType, decodeType),
  };
};

export const prepareEmptyGenericParameterTemplate = (): GenericParameterTemplate => {
  return {
    templateType: TemplateType.GenericParameterTemplate,
    name: prepareStaticTemplate(""),
    baseType: prepareEmptyDefinitionRefTemplate(),
  };
};

export const isGenericParameterTemplate = <T>(template: Template<T>): template is GenericParameterTemplate => {
  return template.templateType === TemplateType.GenericParameterTemplate;
};
