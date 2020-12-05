import { ExtendedPresentationElement } from "./ExtendedPresentationElement";
import RuleInvocationPresentationElement, { decodeRuleInvocationPresentationElement } from "./RuleInvocationPresentationElement";
import NaturalLanguageProvision, { decodeNaturalLanguageProvision } from "./NaturalLanguageProvision";
import Rule, { decodeRule } from "./Rule";
import MetaValue from "./MetaValue";
import { List } from "immutable";

export const decodeExtendedRuleInvocationPresentationElement = (json: any): ExtendedRuleInvocationPresentationElement => {
  return new ExtendedRuleInvocationPresentationElement(
    decodeRuleInvocationPresentationElement(json.presentationElement),
    decodeNaturalLanguageProvision(json.naturalLanguageDocumentObject),
    decodeRule(json.rule)
  );
};

export class ExtendedRuleInvocationPresentationElement implements ExtendedPresentationElement {
  presentationElement: RuleInvocationPresentationElement;
  naturalLanguageDocumentObject: NaturalLanguageProvision;
  rule: Rule;

  constructor(presentationElement: RuleInvocationPresentationElement, naturalLanguageDocumentObject: NaturalLanguageProvision, rule: Rule) {
    this.presentationElement = presentationElement;
    this.naturalLanguageDocumentObject = naturalLanguageDocumentObject;
    this.rule = rule;
  }

  withRuleInvocationArguments(ruleInvocationArguments: List<MetaValue>): ExtendedRuleInvocationPresentationElement {
    return this.withPresentationElement(this.presentationElement.withRuleInvocationArguments(ruleInvocationArguments));
  }

  withPresentationElement(presentationElement: RuleInvocationPresentationElement): ExtendedRuleInvocationPresentationElement {
    return new ExtendedRuleInvocationPresentationElement(presentationElement, this.naturalLanguageDocumentObject, this.rule);
  }
}
