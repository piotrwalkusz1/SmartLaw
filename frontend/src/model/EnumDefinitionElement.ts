import Element, { elementMeta, ElementType } from "./Element";
import { List } from "immutable";
import EnumVariant, { EnumVariantUtils } from "./EnumVariant";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta, nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface EnumDefinitionElement extends Element {
  name: string;
  description: string | null;
  variants: List<EnumVariant>;
}

export interface EnumDefinitionElementTemplate extends WrapWithTemplate<EnumDefinitionElement> {}

export const EnumDefinitionElementUtils = buildDerivativeModelUtilsWithTemplate<
  EnumDefinitionElement,
  Element,
  ElementType,
  EnumDefinitionElementTemplate
>(elementMeta, ElementType.EnumDefinition, TemplateType.EnumDefinitionTemplate, {
  name: stringMeta,
  description: nullableMeta(stringMeta),
  variants: listMeta(EnumVariantUtils.metaData),
});
