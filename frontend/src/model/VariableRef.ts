import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";
import Expression, { expressionMeta } from "./Expression";
import { StatementType } from "./Statement";

export default interface VariableRef extends Expression {}

export const variableRefMeta: BaseMetaData<VariableRef, StatementType> = buildBaseMetaData<VariableRef, StatementType>(
  "statementType",
  StatementType,
  expressionMeta,
  {
    statementType: excludeFromTemplate(enumMeta(StatementType)),
  }
);
