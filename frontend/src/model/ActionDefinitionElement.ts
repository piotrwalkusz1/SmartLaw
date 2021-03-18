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

export default interface ActionDefinitionElement extends Element {
  name: string;
  description: string | null;
  arguments: List<ActionArgument>;
  function: FunctionRef;
}

export const decodeActionDefinitionElement = (json: any): ActionDefinitionElement => {
  return {
    elementType: decodeEnum(json.elementType, ElementType),
    id: decodeId(json.id),
    annotations: decodeList(json.annotations, decodeAnnotation),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    arguments: decodeList(json.arguments, decodeActionArgument),
    function: decodeFunctionRef(json.function),
  };
};

export const prepareEmptyActionDefinitionElement = (): ActionDefinitionElement => {
  return {
    elementType: ElementType.ActionDefinition,
    id: prepareEmptyId(),
    annotations: List(),
    name: "",
    description: null,
    arguments: List(),
    function: prepareEmptyFunctionRef(),
  };
};

export interface ActionDefinitionElementTemplate extends WrapWithTemplate<ActionDefinitionElement> {}

export const decodeActionDefinitionElementTemplate = (json: any): ActionDefinitionElementTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    id: decodeTemplate(json.id, decodeId),
    annotations: decodeTemplate(json.annotations, (json) => decodeList(json, decodeAnnotation)),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    arguments: decodeTemplate(json.arguments, (json) => decodeList(json, decodeActionArgument)),
    function: decodeTemplate(json.function, decodeFunctionRef),
  };
};

export const prepareEmptyActionDefinitionElementTemplate = (): ActionDefinitionElementTemplate => {
  return {
    templateType: TemplateType.ActionDefinitionTemplate,
    id: prepareEmptyIdTemplate(),
    annotations: prepareStaticTemplate(List()),
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    arguments: prepareEmptyListTemplate(),
    function: prepareEmptyFunctionRefTemplate(),
  };
};

export const isActionDefinitionElementTemplate = <T>(template: Template<T>): template is ActionDefinitionElementTemplate => {
  return template.templateType === TemplateType.ActionDefinitionTemplate;
};
