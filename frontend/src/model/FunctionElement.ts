import { decodeEnum, decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Element, { ElementType } from "./Element";
import { decodeId, prepareEmptyId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import { List } from "immutable";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareEmptyIdTemplate } from "./IdTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import ActionArgument, { decodeActionArgument } from "./ActionArgument";
import FunctionRef, { decodeFunctionRef, prepareEmptyFunctionRef, prepareEmptyFunctionRefTemplate } from "./FunctionRef";
import { prepareEmptyListTemplate } from "./ListTemplate";
import GenericParameter from "./GenericParameter";
import FunctionArgumentDefinition from "./FunctionArgumentDefinition";

export default interface FunctionElement extends Element {
  name: string;
  description: string | null;
  parameters: List<GenericParameter>;
  arguments: List<FunctionArgumentDefinition>;
  body: List<Statement>;
  result: FunctionResult | null;
}

export const decodeFunctionElement = (json: any): FunctionElement => {
  return {
    elementType: decodeEnum(json.elementType, ElementType),
    id: decodeId(json.id),
    annotations: decodeList(json.annotations, decodeAnnotation),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    arguments: decodeList(json.arguments, decodeActionArgument),
    functionRef: decodeFunctionRef(json.function),
  };
};

export const prepareEmptyFunctionElement = (): FunctionElement => {
  return {
    elementType: ElementType.FunctionElement,
    id: prepareEmptyId(),
    annotations: List(),
    name: "",
    description: null,
    arguments: List(),
    functionRef: prepareEmptyFunctionRef(),
  };
};

export interface FunctionElementTemplate extends WrapWithTemplate<FunctionElement> {}

export const decodeFunctionElementTemplate = (json: any): FunctionElementTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    id: decodeTemplate(json.id, decodeId),
    annotations: decodeTemplate(json.annotations, (json) => decodeList(json, decodeAnnotation)),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    arguments: decodeTemplate(json.arguments, (json) => decodeList(json, decodeActionArgument)),
    functionRef: decodeTemplate(json.function, decodeFunctionRef),
  };
};

export const prepareEmptyFunctionElementTemplate = (): FunctionElementTemplate => {
  return {
    templateType: TemplateType.FunctionElementTemplate,
    id: prepareEmptyIdTemplate(),
    annotations: prepareStaticTemplate(List()),
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    arguments: prepareEmptyListTemplate(),
    functionRef: prepareEmptyFunctionRefTemplate(),
  };
};

export const isFunctionElementTemplate = <T>(template: Template<T>): template is FunctionElementTemplate => {
  return template.templateType === TemplateType.FunctionElementTemplate;
};
