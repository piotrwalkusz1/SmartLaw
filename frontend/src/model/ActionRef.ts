import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import Id, { IdUtils } from "./Id";

export default interface ActionRef {
  actionId: Id;
}

export interface ActionRefTemplate extends WrapWithTemplate<ActionRef> {}

export const ActionRefUtils = buildModelUtilsWithTemplate<ActionRef, ActionRefTemplate>(TemplateType.ActionRefTemplate, {
  actionId: IdUtils.metaData,
});
