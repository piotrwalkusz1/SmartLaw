import { decodeExtendedPresentationElement, ExtendedPresentationElement } from "./ExtendedPresentationElement";
import SectionPresentationElement, { decodeSectionPresentationElement } from "./SectionPresentationElement";
import { List } from "immutable";
import { decodeList, decodeString } from "../utils/Decoders";

export const decodeExtendedSectionPresentationElement = (json: any): ExtendedSectionPresentationElement => {
  return new ExtendedSectionPresentationElement(
    decodeSectionPresentationElement(json.presentationElement),
    decodeString(json.title),
    decodeList(json.nestedExtendedPresentationElements, decodeExtendedPresentationElement)
  );
};

export class ExtendedSectionPresentationElement implements ExtendedPresentationElement {
  presentationElement: SectionPresentationElement;
  title: string;
  nestedExtendedPresentationElements: List<ExtendedPresentationElement>;

  constructor(
    presentationElement: SectionPresentationElement,
    title: string,
    nestedExtendedPresentationElements: List<ExtendedPresentationElement>
  ) {
    this.presentationElement = presentationElement;
    this.title = title;
    this.nestedExtendedPresentationElements = nestedExtendedPresentationElements;
  }
}
