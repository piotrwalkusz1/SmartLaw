import { WrapWithTemplate } from "./WrapWithTemplate";
import { List } from "immutable";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import Expression, { expressionMeta } from "./Expression";
import { StatementType } from "./Statement";
import { EnumValueTemplate } from "./EnumValue";
import Id, { IdUtils } from "./Id";

export default interface Operation extends Expression {
  operationId: Id;
  arguments: List<Expression>;
}

export interface OperationTemplate extends WrapWithTemplate<Operation> {}

export const OperationUtils = buildDerivativeModelUtilsWithTemplate<Operation, Expression, StatementType, EnumValueTemplate>(
  expressionMeta,
  StatementType.Operation,
  TemplateType.OperationTemplate,
  {
    operationId: IdUtils.metaData,
    arguments: listMeta(expressionMeta),
  }
);
