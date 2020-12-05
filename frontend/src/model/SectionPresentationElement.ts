import { List } from "immutable";
import PresentationElement, { decodePresentationElement, PresentationElementType } from "./PresentationElement";
import { decodeList } from "../utils/Decoders";

export const decodeSectionPresentationElement = (json: any): SectionPresentationElement => {
  return new SectionPresentationElement(decodeList(json.presentationElements, decodePresentationElement));
};

export default class SectionPresentationElement implements PresentationElement {
  readonly type: PresentationElementType = PresentationElementType.Section;
  presentationElements: List<PresentationElement>;

  constructor(presentationElements: List<PresentationElement>) {
    this.presentationElements = presentationElements;
  }
}
