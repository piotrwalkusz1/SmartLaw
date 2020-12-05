import NaturalLanguageDocumentObject, { NaturalLanguageDocumentObjectType } from "./NaturalLanguageDocumentObject";
import { decodeEnum, decodeString } from "../utils/Decoders";

export const decodeNaturalLanguageProvision = (json: any): NaturalLanguageProvision => {
  return {
    type: decodeEnum(json.type, NaturalLanguageDocumentObjectType),
    content: decodeString(json.content),
  };
};

export default interface NaturalLanguageProvision extends NaturalLanguageDocumentObject {
  content: string;
}
