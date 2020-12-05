import { decodeNaturalLanguageProvision } from "./NaturalLanguageProvision";
import { decodeNaturalLanguageSection } from "./NaturalLanguageSection";

export enum NaturalLanguageDocumentObjectType {
  Provision = "Provision",
  Section = "Section",
}

export const decodeNaturalLanguageDocumentObject = (json: any): NaturalLanguageDocumentObject => {
  switch (json.type) {
    case NaturalLanguageDocumentObjectType.Provision:
      return decodeNaturalLanguageProvision(json);
    case NaturalLanguageDocumentObjectType.Section:
      return decodeNaturalLanguageSection(json);
    default:
      throw Error("Natural language document object type " + json.type + " is not supported");
  }
};

export default interface NaturalLanguageDocumentObject {
  type: NaturalLanguageDocumentObjectType;
}
