import { WrapWithTemplate } from "./WrapWithTemplate";
import FunctionRef, { FunctionRefUtils } from "./FunctionRef";
import { List } from "immutable";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import Expression, { expressionMeta } from "./Expression";
import { StatementType } from "./Statement";

export default interface FunctionCall extends Expression {
  function: FunctionRef;
  arguments: List<Expression>;
}

export interface FunctionCallTemplate extends WrapWithTemplate<FunctionCall> {}

export const FunctionCallUtils = buildDerivativeModelUtilsWithTemplate<FunctionCall, Expression, StatementType, FunctionCallTemplate>(
  expressionMeta,
  StatementType.FunctionCall,
  TemplateType.FunctionCallTemplate,
  {
    function: FunctionRefUtils.metaData,
    arguments: listMeta(expressionMeta),
  }
);
