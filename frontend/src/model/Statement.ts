import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export enum StatementType {
  Assignment = "Assignment",
  EnumValue = "EnumValue",
  FunctionCall = "FunctionCall",
  Operation = "Operation",
}

export default interface Statement {
  statementType: StatementType;
}

export const statementMeta: BaseMetaData<Statement, StatementType> = buildBaseMetaData<Statement, StatementType>(
  "statementType",
  StatementType,
  null,
  {
    statementType: excludeFromTemplate(enumMeta(StatementType)),
  }
);
