import { List } from "immutable";
import PresentationElement, { decodePresentationElement } from "./PresentationElement";
import { decodeList, decodeString } from "../utils/Decoders";

export const decodeIndentationPresentationElement = (json: any): IndentationPresentationElement => {
  return {
    type: decodeString(json.type),
    presentationElements: decodeList(json.presentationElements, decodePresentationElement),
  };
};

export default interface IndentationPresentationElement extends PresentationElement {
  presentationElements: List<PresentationElement>;
}
