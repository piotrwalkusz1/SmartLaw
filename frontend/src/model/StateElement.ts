import Type, { typeMeta } from "./Type";
import MetaValue, { metaValueMeta } from "./MetaValue";
import Element, { elementMeta, ElementType } from "./Element";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface StateElement extends Element {
  name: string;
  description: string | null;
  type: Type;
  defaultValue: MetaValue | null;
}

export interface StateElementTemplate extends WrapWithTemplate<StateElement> {}

export const StateElementUtils = buildDerivativeModelUtilsWithTemplate<StateElement, Element, ElementType, StateElementTemplate>(
  elementMeta,
  ElementType.State,
  TemplateType.StateTemplate,
  {
    name: stringMeta,
    description: nullableMeta(stringMeta),
    type: typeMeta,
    defaultValue: nullableMeta(metaValueMeta),
  }
);
