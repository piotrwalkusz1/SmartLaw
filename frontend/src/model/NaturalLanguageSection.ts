import NaturalLanguageDocumentObject, {
  decodeNaturalLanguageDocumentObject,
  NaturalLanguageDocumentObjectType,
} from "./NaturalLanguageDocumentObject";
import { List } from "immutable";
import { decodeEnum, decodeList, decodeString } from "../utils/Decoders";

export const decodeNaturalLanguageSection = (json: any): NaturalLanguageSection => {
  return {
    type: decodeEnum(json.type, NaturalLanguageDocumentObjectType),
    title: decodeString(json.title),
    provisions: decodeList(json.provisions, decodeNaturalLanguageDocumentObject),
  };
};

export default interface NaturalLanguageSection extends NaturalLanguageDocumentObject {
  title: string;
  provisions: List<NaturalLanguageDocumentObject>;
}
