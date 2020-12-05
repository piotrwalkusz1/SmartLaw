import { decodeIndentationPresentationElement } from "./IndentationPresentationElement";
import { decodeRuleInvocationPresentationElement } from "./RuleInvocationPresentationElement";

export enum PresentationElementType {
  Indentation = "Indentation",
  RuleInvocation = "RuleInvocation",
}

export const decodePresentationElement = (json: any): PresentationElement => {
  switch (json.type) {
    case PresentationElementType.Indentation:
      return decodeIndentationPresentationElement(json);
    case PresentationElementType.RuleInvocation:
      return decodeRuleInvocationPresentationElement(json);
    default:
      throw Error("Presentation element with type " + json.type + " is not supported");
  }
};

export default interface PresentationElement {
  type: string;
}
