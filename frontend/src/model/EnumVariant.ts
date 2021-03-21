import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface EnumVariant {
  name: string;
  description: string | null;
}

export interface EnumVariantTemplate extends WrapWithTemplate<EnumVariant> {}

export const EnumVariantUtils = buildModelUtilsWithTemplate<EnumVariant, EnumVariantTemplate>(TemplateType.EnumVariantTemplate, {
  name: stringMeta,
  description: nullableMeta(stringMeta),
});
