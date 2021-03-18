import { decodeEnum, decodeNullable, decodeString } from "../utils/Decoders";
import DefinitionRef, { decodeDefinitionRef, prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";

export default interface ActionArgument {
  name: string;
  description: string | null;
  type: DefinitionRef;
}

export const decodeActionArgument = (json: any): ActionArgument => {
  return {
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    type: decodeDefinitionRef(json.type),
  };
};

export const prepareEmptyActionArgument = (): ActionArgument => {
  return {
    name: "",
    description: null,
    type: prepareDefinitionRef(TYPES.UINT),
  };
};

export interface ActionArgumentTemplate extends WrapWithTemplate<ActionArgument> {}

export const decodeActionArgumentTemplate = (json: any): ActionArgumentTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeDefinitionRef),
  };
};

export const prepareEmptyActionArgumentTemplate = (): ActionArgumentTemplate => {
  return {
    templateType: TemplateType.ActionArgumentTemplate,
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    type: prepareEmptyDefinitionRefTemplate(),
  };
};

export const isActionArgumentTemplate = <T>(template: Template<T>): template is ActionArgumentTemplate => {
  return template.templateType === TemplateType.ActionArgumentTemplate;
};
