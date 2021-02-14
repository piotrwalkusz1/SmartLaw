import { decodeContract } from "./Contract";
import { decodeLibrary } from "./Library";

export enum DocumentType {
  Contract = "Contract",
  Library = "Library",
}

export const decodeDocument = (json: any): Document => {
  switch (json.documentType) {
    case DocumentType.Contract:
      return decodeContract(json);
    case DocumentType.Library:
      return decodeLibrary(json);
    default:
      throw Error("Document with type " + json.documentType + " is not supported");
  }
};

export default interface Document {
  documentType: string;
}
