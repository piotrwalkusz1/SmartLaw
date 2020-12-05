import RuleInvocation, { decodeRuleInvocation } from "./RuleInvocation";
import PresentationElement from "./PresentationElement";
import { decodeString } from "../utils/Decoders";

export const decodeRuleInvocationPresentationElement = (json: any): RuleInvocationPresentationElement => {
  return {
    type: decodeString(json.type),
    ruleInvocation: decodeRuleInvocation(json.ruleInvocation),
  };
};

export default interface RuleInvocationPresentationElement extends PresentationElement {
  ruleInvocation: RuleInvocation;
}
