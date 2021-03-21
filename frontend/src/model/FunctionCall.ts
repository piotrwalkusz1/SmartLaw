import { WrapWithTemplate } from "./WrapWithTemplate";
import FunctionRef, { FunctionRefUtils } from "./FunctionRef";
import { List } from "immutable";
import FunctionArgument, { functionArgumentMeta } from "./FunctionArgument";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface FunctionCall {
  function: FunctionRef;
  arguments: List<FunctionArgument>;
}

export interface FunctionCallTemplate extends WrapWithTemplate<FunctionCall> {}

export const FunctionCallUtils = buildModelUtilsWithTemplate<FunctionCall, FunctionCallTemplate>(TemplateType.FunctionCallTemplate, {
  function: FunctionRefUtils.metaData,
  arguments: listMeta(functionArgumentMeta),
});
