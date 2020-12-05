import { List } from "immutable";
import PresentationElement, { decodePresentationElement } from "./PresentationElement";
import Document from "./Document";
import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Id, { decodeId } from "./Id";

export const decodeContract = (json: any): Contract => {
  return {
    documentType: decodeString(json.documentType),
    id: decodeId(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    presentationElements: decodeList(json.presentationElements, decodePresentationElement),
  };
};

export default interface Contract extends Document {
  id: Id;
  name: string;
  description: string | null;
  presentationElements: List<PresentationElement>;
}
