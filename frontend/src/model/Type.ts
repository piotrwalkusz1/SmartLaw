import { decodeDefinitionRef } from "./DefinitionRef";

export enum TypeKind {
  DefinitionRef = "DefinitionRef",
}

export const decodeType = (json: any): Type => {
  switch (json.type) {
    case TypeKind.DefinitionRef:
      return decodeDefinitionRef(json);
    default:
      throw Error("Decoding type " + json.type + " is not supported");
  }
};

export default interface Type {
  type: string;
}
