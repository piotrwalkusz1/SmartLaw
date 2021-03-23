import Id, { IdUtils } from "./Id";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import Expression, { expressionMeta } from "./Expression";
import { StatementType } from "./Statement";

export default interface EnumValue extends Expression {
  enumDefinition: Id;
  value: string;
}

export interface EnumValueTemplate extends WrapWithTemplate<EnumValue> {}

export const EnumValueUtils = buildDerivativeModelUtilsWithTemplate<EnumValue, Expression, StatementType, EnumValueTemplate>(
  expressionMeta,
  StatementType.EnumValue,
  TemplateType.EnumValueTemplate,
  {
    enumDefinition: IdUtils.metaData,
    value: stringMeta,
  }
);
