import Id, { IdUtils } from "./Id";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface EnumValue {
  enumDefinition: Id;
  value: string;
}

export interface EnumValueTemplate extends WrapWithTemplate<EnumValue> {}

export const EnumValueUtils = buildModelUtilsWithTemplate<EnumValue, EnumValueTemplate>(TemplateType.EnumValueTemplate, {
  enumDefinition: IdUtils.metaData,
  value: stringMeta,
});
