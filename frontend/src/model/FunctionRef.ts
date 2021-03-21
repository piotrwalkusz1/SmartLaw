import { WrapWithTemplate } from "./WrapWithTemplate";
import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import Type, { typeMeta } from "./Type";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface FunctionRef {
  id: Id;
  parameters: List<Type>;
}

export interface FunctionRefTemplate extends WrapWithTemplate<FunctionRef> {}

export const FunctionRefUtils = buildModelUtilsWithTemplate<FunctionRef, FunctionRefTemplate>(TemplateType.FunctionRefTemplate, {
  id: IdUtils.metaData,
  parameters: listMeta(typeMeta),
});
