import { decodeNaturalLanguageProvision } from "./NaturalLanguageProvision";
import { decodeNaturalLanguageSection } from "./NaturalLanguageSection";

export const decodeNaturalLanguageDocumentObject = (json: any): NaturalLanguageDocumentObject => {
  switch (json.type) {
    case "Provision":
      return decodeNaturalLanguageProvision(json);
    case "Section":
      return decodeNaturalLanguageSection(json);
    default:
      throw Error("Natural language document object type " + json.type + " is not supported");
  }
};

export default interface NaturalLanguageDocumentObject {}
