import Statement, { statementMeta, StatementType } from "./Statement";
import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export default interface Expression extends Statement {}

export const expressionMeta: BaseMetaData<Expression, StatementType> = buildBaseMetaData<Expression, StatementType>(
  "statementType",
  StatementType,
  statementMeta,
  {
    statementType: excludeFromTemplate(enumMeta(StatementType)),
  }
);
