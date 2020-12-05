import { decodeContract } from "./Contract";

export enum DocumentType {
  Contract = "Contract",
}

export const decodeDocument = (json: any): Document => {
  switch (json.documentType) {
    case DocumentType.Contract:
      return decodeContract(json);
    default:
      throw Error("Document with type " + json.documentType + " is not supported");
  }
};

export default interface Document {
  documentType: string;
}
