import { decodeExtendedPresentationElement, ExtendedPresentationElement } from "./ExtendedPresentationElement";
import { List } from "immutable";
import { decodeOutputMessage, OutputMessage } from "./OutputMessage";
import { decodeList } from "../utils/Decoders";

export const decodeExtendPresentationElementsResultDto = (json: any): ExtendPresentationElementsResultDto => {
  return new ExtendPresentationElementsResultDto(
    decodeList(json.extendedPresentationElements, decodeExtendedPresentationElement),
    decodeList(json.outputMessages, decodeOutputMessage)
  );
};

export class ExtendPresentationElementsResultDto {
  extendedPresentationElements: List<ExtendedPresentationElement>;
  outputMessages: List<OutputMessage>;

  constructor(extendedPresentationElements: List<ExtendedPresentationElement>, outputMessages: List<OutputMessage>) {
    this.extendedPresentationElements = extendedPresentationElements;
    this.outputMessages = outputMessages;
  }
}
