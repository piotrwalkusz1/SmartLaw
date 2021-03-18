import { decodeEnum, decodeList } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import Id, { decodeId, prepareEmptyId } from "./Id";
import { List } from "immutable";
import Type, { decodeType } from "./Type";
import { prepareEmptyIdTemplate } from "./IdTemplate";
import { prepareEmptyListTemplate } from "./ListTemplate";

export default interface FunctionRef {
  id: Id;
  parameters: List<Type>;
}

export const decodeFunctionRef = (json: any): FunctionRef => {
  return {
    id: decodeId(json.id),
    parameters: decodeList(json.parameters, decodeType),
  };
};

export const prepareEmptyFunctionRef = (): FunctionRef => {
  return {
    id: prepareEmptyId(),
    parameters: List(),
  };
};

export interface FunctionRefTemplate extends WrapWithTemplate<FunctionRef> {}

export const decodeFunctionRefTemplate = (json: any): FunctionRefTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    id: decodeTemplate(json.id, decodeId),
    parameters: decodeTemplate(json.parameters, (json) => decodeList(json, decodeType)),
  };
};

export const prepareEmptyFunctionRefTemplate = (): FunctionRefTemplate => {
  return {
    templateType: TemplateType.FunctionRefTemplate,
    id: prepareEmptyIdTemplate(),
    parameters: prepareEmptyListTemplate(),
  };
};

export const isFunctionRefTemplate = <T>(template: Template<T>): template is FunctionRefTemplate => {
  return template.templateType === TemplateType.FunctionRefTemplate;
};
