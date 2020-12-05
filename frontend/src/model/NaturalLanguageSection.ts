import NaturalLanguageDocumentObject, { decodeNaturalLanguageDocumentObject } from "./NaturalLanguageDocumentObject";
import { List } from "immutable";
import { decodeList, decodeString } from "../utils/Decoders";

export const decodeNaturalLanguageSection = (json: any): NaturalLanguageSection => {
  return new NaturalLanguageSection(decodeString(json.title), decodeList(json.provisions, decodeNaturalLanguageDocumentObject));
};

export default class NaturalLanguageSection implements NaturalLanguageDocumentObject {
  title: string;
  provisions: List<NaturalLanguageDocumentObject>;

  constructor(title: string, provisions: List<NaturalLanguageDocumentObject>) {
    this.title = title;
    this.provisions = provisions;
  }
}
