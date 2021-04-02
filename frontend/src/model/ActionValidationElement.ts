import Element, { elementMeta, ElementType } from "./Element";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import Expression, { expressionMeta } from "./Expression";
import ActionRef, { ActionRefUtils } from "./ActionRef";

export default interface ActionValidationElement extends Element {
  name: string;
  description: string | null;
  action: ActionRef;
  condition: Expression;
}

export interface ActionValidationElementTemplate extends WrapWithTemplate<ActionValidationElement> {}

export const ActionValidationElementUtils = buildDerivativeModelUtilsWithTemplate<
  ActionValidationElement,
  Element,
  ElementType,
  ActionValidationElementTemplate
>(elementMeta, ElementType.ActionValidation, TemplateType.ActionValidationTemplate, {
  name: stringMeta,
  description: nullableMeta(stringMeta),
  action: ActionRefUtils.metaData,
  condition: expressionMeta,
});
