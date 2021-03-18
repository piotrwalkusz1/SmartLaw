import { decodeEnum, decodeNullable, decodeString } from "../utils/Decoders";
import DefinitionRef, { decodeDefinitionRef, prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";

export default interface FunctionArgument {
  name: string;
  description: string | null;
  type: DefinitionRef;
}

export const decodeFunctionArgument = (json: any): FunctionArgument => {
  return {
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    type: decodeDefinitionRef(json.type),
  };
};

export const prepareEmptyFunctionArgument = (): FunctionArgument => {
  return {
    name: "",
    description: null,
    type: prepareDefinitionRef(TYPES.UINT),
  };
};

export interface FunctionArgumentTemplate extends WrapWithTemplate<FunctionArgument> {}

export const decodeFunctionArgumentTemplate = (json: any): FunctionArgumentTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeDefinitionRef),
  };
};

export const prepareEmptyFunctionArgumentTemplate = (): FunctionArgumentTemplate => {
  return {
    templateType: TemplateType.FunctionArgumentTemplate,
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    type: prepareEmptyDefinitionRefTemplate(),
  };
};

export const isFunctionArgumentTemplate = <T>(template: Template<T>): template is FunctionArgumentTemplate => {
  return template.templateType === TemplateType.FunctionArgumentTemplate;
};
