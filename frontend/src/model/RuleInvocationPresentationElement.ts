import RuleInvocation, { RuleInvocationUtils } from "./RuleInvocation";
import PresentationElement, { PresentationElementType } from "./PresentationElement";
import MetaValue from "./MetaValue";
import { Map } from "immutable";

export const decodeRuleInvocationPresentationElement = (json: any): RuleInvocationPresentationElement => {
  return new RuleInvocationPresentationElement(RuleInvocationUtils.decode(json.ruleInvocation));
};

export default class RuleInvocationPresentationElement implements PresentationElement {
  readonly type: PresentationElementType = PresentationElementType.RuleInvocation;
  ruleInvocation: RuleInvocation;

  constructor(ruleInvocation: RuleInvocation) {
    this.ruleInvocation = ruleInvocation;
  }

  withRuleInvocationArguments(ruleInvocationArguments: Map<string, MetaValue>): RuleInvocationPresentationElement {
    return this.withRuleInvocation({ ...this.ruleInvocation, arguments: ruleInvocationArguments });
  }

  withRuleInvocation(ruleInvocation: RuleInvocation): RuleInvocationPresentationElement {
    return new RuleInvocationPresentationElement(ruleInvocation);
  }
}
