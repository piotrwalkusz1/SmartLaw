import Statement from "./Statement";
import { decodeEnum } from "../utils/Decoders";

export enum ExpressionType {
  EnumValue = "EnumValue",
  FunctionCall = "FunctionCall",
  Operation = "Operation",
}

export const decodeExpression = (json: any): Expression => {
  const expressionType = decodeEnum(json.ExpressionType, ExpressionType);

  switch (expressionType) {
    case ExpressionType.EnumValue:
  }
};

export default interface Expression extends Statement {}
