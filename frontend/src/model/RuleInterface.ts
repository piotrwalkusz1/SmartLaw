import Id, { IdUtils } from "./Id";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface RuleInterface {
  id: Id;
  name: string;
  description: string | null;
}

export interface RuleInterfaceTemplate extends WrapWithTemplate<RuleInterface> {}

export const RuleInterfaceUtils = buildModelUtilsWithTemplate<RuleInterface, RuleInterfaceTemplate>(TemplateType.RuleInterfaceTemplate, {
  id: IdUtils.metaData,
  name: stringMeta,
  description: nullableMeta(stringMeta),
});
