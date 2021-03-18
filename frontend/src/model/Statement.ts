import { decodeEnum } from "../utils/Decoders";
import { ExpressionType } from "./Expression";

export enum StatementType {
  Assignment = "Assignment",
  EnumValue = "EnumValue",
  FunctionCall = "FunctionCall",
  Operation = "Operation",
}

export const decodeStatement = (json: any): Statement => {
  const statementType = decodeEnum(json.statementType, StatementType);

  switch (statementType) {
  }
};

export default interface Statement {
  statementType: StatementType;
}
