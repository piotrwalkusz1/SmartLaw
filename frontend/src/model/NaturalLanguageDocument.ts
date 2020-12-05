import { List } from "immutable";
import NaturalLanguageDocumentObject, { decodeNaturalLanguageDocumentObject } from "./NaturalLanguageDocumentObject";
import { decodeList, decodeString } from "../utils/Decoders";

export const decodeNaturalLanguageDocument = (json: any): NaturalLanguageDocument => {
  return {
    title: decodeString(json.title),
    objects: decodeList(json.objects, decodeNaturalLanguageDocumentObject),
  };
};

export default interface NaturalLanguageDocument {
  title: String;
  objects: List<NaturalLanguageDocumentObject>;
}
