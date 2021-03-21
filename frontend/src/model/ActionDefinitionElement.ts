import Element, { elementMeta, ElementType } from "./Element";
import { List } from "immutable";
import { WrapWithTemplate } from "./WrapWithTemplate";
import ActionArgument, { ActionArgumentUtils } from "./ActionArgument";
import FunctionRef, { FunctionRefUtils } from "./FunctionRef";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta, nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface ActionDefinitionElement extends Element {
  name: string;
  description: string | null;
  arguments: List<ActionArgument>;
  function: FunctionRef;
}

export interface ActionDefinitionElementTemplate extends WrapWithTemplate<ActionDefinitionElement> {}

export const ActionDefinitionElementUtils = buildDerivativeModelUtilsWithTemplate<
  ActionDefinitionElement,
  Element,
  ElementType,
  ActionDefinitionElementTemplate
>(elementMeta, ElementType.ActionDefinition, TemplateType.ActionDefinitionTemplate, {
  name: stringMeta,
  description: nullableMeta(stringMeta),
  arguments: listMeta(ActionArgumentUtils.metaData),
  function: FunctionRefUtils.metaData,
});
