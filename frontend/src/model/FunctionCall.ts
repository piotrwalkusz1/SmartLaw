import { decodeEnum, decodeList } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import FunctionRef, { decodeFunctionRef, prepareEmptyFunctionRef, prepareEmptyFunctionRefTemplate } from "./FunctionRef";
import { List } from "immutable";
import FunctionArgument, { decodeFunctionArgument } from "./FunctionArgument";
import { prepareEmptyListTemplate } from "./ListTemplate";

export default interface FunctionCall {
  function: FunctionRef;
  arguments: List<FunctionArgument>;
}

export const decodeFunctionCall = (json: any): FunctionCall => {
  return {
    function: decodeFunctionRef(json.function),
    arguments: decodeList(json.arguments, decodeFunctionArgument),
  };
};

export const prepareEmptyFunctionCall = (): FunctionCall => {
  return {
    function: prepareEmptyFunctionRef(),
    arguments: List(),
  };
};

export interface FunctionCallTemplate extends WrapWithTemplate<FunctionCall> {}

export const decodeFunctionCallTemplate = (json: any): FunctionCallTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    function: decodeTemplate(json.function, decodeFunctionRef),
    arguments: decodeTemplate(json.arguments, (json) => decodeList(json, decodeFunctionArgument)),
  };
};

export const prepareEmptyFunctionCallTemplate = (): FunctionCallTemplate => {
  return {
    templateType: TemplateType.FunctionCallTemplate,
    function: prepareEmptyFunctionRefTemplate(),
    arguments: prepareEmptyListTemplate(),
  };
};

export const isFunctionCallTemplate = <T>(template: Template<T>): template is FunctionCallTemplate => {
  return template.templateType === TemplateType.FunctionCallTemplate;
};
