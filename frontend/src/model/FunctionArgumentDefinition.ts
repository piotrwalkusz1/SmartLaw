import { decodeEnum, decodeNullable, decodeString } from "../utils/Decoders";
import DefinitionRef, { decodeDefinitionRef, prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";
import Type, { decodeType } from "./Type";

export default interface FunctionArgumentDefinition {
  name: string;
  description: string | null;
  type: Type;
}

export const decodeFunctionArgumentDefinition = (json: any): FunctionArgumentDefinition => {
  return {
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    type: decodeType(json.type),
  };
};

export const prepareEmptyFunctionArgumentDefinition = (): FunctionArgumentDefinition => {
  return {
    name: "",
    description: null,
    type: prepareDefinitionRef(TYPES.UINT),
  };
};

export interface FunctionArgumentDefinitionTemplate extends WrapWithTemplate<FunctionArgumentDefinition> {}

export const decodeFunctionArgumentDefinitionTemplate = (json: any): FunctionArgumentDefinitionTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeType),
  };
};

export const prepareEmptyFunctionArgumentDefinitionTemplate = (): FunctionArgumentDefinitionTemplate => {
  return {
    templateType: TemplateType.FunctionArgumentDefinitionTemplate,
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    type: prepareEmptyDefinitionRefTemplate(),
  };
};

export const isFunctionArgumentDefinitionTemplate = <T>(template: Template<T>): template is FunctionArgumentDefinitionTemplate => {
  return template.templateType === TemplateType.FunctionArgumentDefinitionTemplate;
};
