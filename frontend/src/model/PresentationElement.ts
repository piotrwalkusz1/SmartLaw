import { decodeSectionPresentationElement } from "./SectionPresentationElement";
import { decodeRuleInvocationPresentationElement } from "./RuleInvocationPresentationElement";

export enum PresentationElementType {
  Section = "Section",
  RuleInvocation = "RuleInvocation",
}

export const decodePresentationElement = (json: any): PresentationElement => {
  switch (json.type) {
    case PresentationElementType.Section:
      return decodeSectionPresentationElement(json);
    case PresentationElementType.RuleInvocation:
      return decodeRuleInvocationPresentationElement(json);
    default:
      throw Error("Presentation element with type " + json.type + " is not supported");
  }
};

export default interface PresentationElement {
  readonly type: PresentationElementType;
}
