import NaturalLanguageDocumentObject from "./NaturalLanguageDocumentObject";
import { decodeString } from "../utils/Decoders";

export const decodeNaturalLanguageProvision = (json: any): NaturalLanguageProvision => {
  return new NaturalLanguageProvision(decodeString(json.content));
};

export default class NaturalLanguageProvision implements NaturalLanguageDocumentObject {
  content: string;

  constructor(content: string) {
    this.content = content;
  }
}
