import MetaValue, { metaValueMeta, MetaValueType } from "./MetaValue";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface MetaPrimitiveValue extends MetaValue {
  value: string;
}

export interface MetaPrimitiveValueTemplate extends WrapWithTemplate<MetaPrimitiveValue> {}

export const MetaPrimitiveValueUtils = buildDerivativeModelUtilsWithTemplate<
  MetaPrimitiveValue,
  MetaValue,
  MetaValueType,
  MetaPrimitiveValueTemplate
>(metaValueMeta, MetaValueType.Primitive, TemplateType.MetaPrimitiveValueTemplate, {
  value: stringMeta,
});
