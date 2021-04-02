import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import Expression, { expressionMeta } from "./Expression";
import { StatementType } from "./Statement";
import { EnumValueTemplate } from "./EnumValue";
import MetaValue, { metaValueMeta } from "./MetaValue";
import Type, { typeMeta } from "./Type";

export default interface ConstantValue extends Expression {
  value: MetaValue;
  type: Type;
}

export interface ConstantValueTemplate extends WrapWithTemplate<ConstantValue> {}

export const ConstantValueUtils = buildDerivativeModelUtilsWithTemplate<ConstantValue, Expression, StatementType, EnumValueTemplate>(
  expressionMeta,
  StatementType.ConstantValue,
  TemplateType.ConstantValueTemplate,
  {
    value: metaValueMeta,
    type: typeMeta,
  }
);
