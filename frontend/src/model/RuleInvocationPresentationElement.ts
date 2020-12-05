import RuleInvocation, { decodeRuleInvocation } from "./RuleInvocation";
import PresentationElement, { PresentationElementType } from "./PresentationElement";
import MetaValue from "./MetaValue";
import { List } from "immutable";

export const decodeRuleInvocationPresentationElement = (json: any): RuleInvocationPresentationElement => {
  return new RuleInvocationPresentationElement(decodeRuleInvocation(json.ruleInvocation));
};

export default class RuleInvocationPresentationElement implements PresentationElement {
  readonly type: PresentationElementType = PresentationElementType.RuleInvocation;
  ruleInvocation: RuleInvocation;

  constructor(ruleInvocation: RuleInvocation) {
    this.ruleInvocation = ruleInvocation;
  }

  withRuleInvocationArguments(ruleInvocationArguments: List<MetaValue>): RuleInvocationPresentationElement {
    return this.withRuleInvocation({ ...this.ruleInvocation, arguments: ruleInvocationArguments });
  }

  withRuleInvocation(ruleInvocation: RuleInvocation): RuleInvocationPresentationElement {
    return new RuleInvocationPresentationElement(ruleInvocation);
  }
}
