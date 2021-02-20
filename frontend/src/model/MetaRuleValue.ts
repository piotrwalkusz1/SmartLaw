import MetaValue, { MetaValueType } from "./MetaValue";
import RuleInvocation, { decodeRuleInvocation } from "./RuleInvocation";

export const decodeMetaRuleValue = (json: any): MetaRuleValue => {
  return {
    type: MetaValueType.Rule,
    ruleInvocation: decodeRuleInvocation(json.ruleInvocation),
  };
};

export default interface MetaRuleValue extends MetaValue {
  ruleInvocation: RuleInvocation;
}
