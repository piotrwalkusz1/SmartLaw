import { ExtendedPresentationElement } from "./ExtendedPresentationElement";
import RuleInvocationPresentationElement, { decodeRuleInvocationPresentationElement } from "./RuleInvocationPresentationElement";
import NaturalLanguageProvision, { decodeNaturalLanguageProvision } from "./NaturalLanguageProvision";
import Rule, { decodeRule } from "./Rule";
import MetaValue from "./MetaValue";
import { List, Map } from "immutable";
import { decodeValidationResult, ValidationResult } from "./ValidationResult";
import { decodeList, decodeMap, decodeNullable } from "../utils/Decoders";

export const decodeExtendedRuleInvocationPresentationElement = (json: any): ExtendedRuleInvocationPresentationElement => {
  return new ExtendedRuleInvocationPresentationElement(
    decodeRuleInvocationPresentationElement(json.presentationElement),
    decodeNaturalLanguageProvision(json.naturalLanguageDocumentObject),
    decodeNullable(json.rule, decodeRule),
    decodeMap(json.validationResults, (validationResult) => decodeList(validationResult, decodeValidationResult))
  );
};

export class ExtendedRuleInvocationPresentationElement implements ExtendedPresentationElement {
  presentationElement: RuleInvocationPresentationElement;
  naturalLanguageDocumentObject: NaturalLanguageProvision;
  rule: Rule | null;
  validationResults: Map<String, List<ValidationResult>>;

  constructor(
    presentationElement: RuleInvocationPresentationElement,
    naturalLanguageDocumentObject: NaturalLanguageProvision,
    rule: Rule | null,
    validationResults: Map<String, List<ValidationResult>>
  ) {
    this.presentationElement = presentationElement;
    this.naturalLanguageDocumentObject = naturalLanguageDocumentObject;
    this.rule = rule;
    this.validationResults = validationResults;
  }

  withRuleInvocationArguments(ruleInvocationArguments: Map<String, MetaValue>): ExtendedRuleInvocationPresentationElement {
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
