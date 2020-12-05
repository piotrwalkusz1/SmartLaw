import PresentationElement from "./PresentationElement";
import { decodeExtendedSectionPresentationElement } from "./ExtendedSectionPresentationElement";
import { decodeExtendedRuleInvocationPresentationElement } from "./ExtendedRuleInvocationPresentationElement";

export const decodeExtendedPresentationElement = <T extends PresentationElement>(json: any): ExtendedPresentationElement => {
  switch (json.type) {
    case "Section":
      return decodeExtendedSectionPresentationElement(json);
    case "RuleInvocation":
      return decodeExtendedRuleInvocationPresentationElement(json);
    default:
      throw Error("ExtendedPresentationElement type " + json.type + " is not supported");
  }
};

export interface ExtendedPresentationElement {
  presentationElement: PresentationElement;
}
