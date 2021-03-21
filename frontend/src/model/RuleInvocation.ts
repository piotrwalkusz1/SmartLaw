import Id, { IdUtils } from "./Id";
import MetaValue, { metaValueMeta } from "./MetaValue";
import { Map } from "immutable";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { mapMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface RuleInvocation {
  ruleId: Id;
  arguments: Map<string, MetaValue>;
}

export interface RuleInvocationTemplate extends WrapWithTemplate<RuleInvocation> {}

export const RuleInvocationUtils = buildModelUtilsWithTemplate<RuleInvocation, RuleInvocationTemplate>(
  TemplateType.RuleInvocationTemplate,
  {
    ruleId: IdUtils.metaData,
    arguments: mapMeta(metaValueMeta),
  }
);
