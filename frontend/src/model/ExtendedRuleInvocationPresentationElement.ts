import { ExtendedPresentationElement } from "./ExtendedPresentationElement";
import RuleInvocationPresentationElement, { decodeRuleInvocationPresentationElement } from "./RuleInvocationPresentationElement";
import NaturalLanguageProvision, { decodeNaturalLanguageProvision } from "./NaturalLanguageProvision";
import Rule, { decodeRule } from "./Rule";
import MetaValue from "./MetaValue";
import { List } from "immutable";
import { decodeValidationResult, ValidationResult } from "./ValidationResult";
import { decodeList } from "../utils/Decoders";

export const decodeExtendedRuleInvocationPresentationElement = (json: any): ExtendedRuleInvocationPresentationElement => {
  return new ExtendedRuleInvocationPresentationElement(
    decodeRuleInvocationPresentationElement(json.presentationElement),
    decodeNaturalLanguageProvision(json.naturalLanguageDocumentObject),
    decodeRule(json.rule),
    decodeList(json.validationResults, (validationResult) => decodeList(validationResult, decodeValidationResult))
  );
};

export class ExtendedRuleInvocationPresentationElement implements ExtendedPresentationElement {
  presentationElement: RuleInvocationPresentationElement;
  naturalLanguageDocumentObject: NaturalLanguageProvision;
  rule: Rule;
  validationResults: List<List<ValidationResult>>;

  constructor(
    presentationElement: RuleInvocationPresentationElement,
    naturalLanguageDocumentObject: NaturalLanguageProvision,
    rule: Rule,
    validationResults: List<List<ValidationResult>>
  ) {
    this.presentationElement = presentationElement;
    this.naturalLanguageDocumentObject = naturalLanguageDocumentObject;
    this.rule = rule;
    this.validationResults = validationResults;
  }

  withRuleInvocationArguments(ruleInvocationArguments: List<MetaValue>): ExtendedRuleInvocationPresentationElement {
    return this.withPresentationElement(this.presentationElement.withRuleInvocationArguments(ruleInvocationArguments));
  }

  withPresentationElement(presentationElement: RuleInvocationPresentationElement): ExtendedRuleInvocationPresentationElement {
    return new ExtendedRuleInvocationPresentationElement(
      presentationElement,
      this.naturalLanguageDocumentObject,
      this.rule,
      this.validationResults
    );
  }
}
