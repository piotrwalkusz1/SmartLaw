import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import Type, { typeMeta } from "./Type";
import Expression, { expressionMeta } from "./Expression";
import { TemplateType } from "./TemplateType";

export default interface FunctionResult {
  expression: Expression;
  type: Type;
}

export interface FunctionResultTemplate extends WrapWithTemplate<FunctionResult> {}

export const FunctionResultUtils = buildModelUtilsWithTemplate<FunctionResult, FunctionResultTemplate>(
  TemplateType.FunctionResultTemplate,
  {
    expression: expressionMeta,
    type: typeMeta,
  }
);
