import MetaValue, { metaValueMeta, MetaValueType } from "./MetaValue";
import RuleInvocation, { RuleInvocationUtils } from "./RuleInvocation";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";

export default interface MetaRuleValue extends MetaValue {
  ruleInvocation: RuleInvocation;
}

export interface MetaRuleValueTemplate extends WrapWithTemplate<MetaRuleValue> {}

export const MetaRuleValueUtils = buildDerivativeModelUtilsWithTemplate<MetaRuleValue, MetaValue, MetaValueType, MetaRuleValueTemplate>(
  metaValueMeta,
  MetaValueType.Rule,
  TemplateType.MetaRuleValueTemplate,
  {
    ruleInvocation: RuleInvocationUtils.metaData,
  }
);
