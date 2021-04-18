import MetaValue, { metaValueMeta, MetaValueType } from "./MetaValue";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { mapMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import { Map } from "immutable";

export default interface MetaMapValue extends MetaValue {
  values: Map<string, MetaValue>;
}

export interface MetaMapValueTemplate extends WrapWithTemplate<MetaMapValue> {}

export const MetaPrimitiveValueUtils = buildDerivativeModelUtilsWithTemplate<MetaMapValue, MetaValue, MetaValueType, MetaMapValueTemplate>(
  metaValueMeta,
  MetaValueType.Map,
  TemplateType.MetaMapValueTemplate,
  {
    values: mapMeta(metaValueMeta),
  }
);
