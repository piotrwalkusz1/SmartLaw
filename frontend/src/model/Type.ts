import { decodeDefinitionRef } from "./DefinitionRef";
import { decodeInterfaceRef } from "./InterfaceRef";
import { decodeGenericType } from "./GenericType";

export enum TypeKind {
  DefinitionRef = "DefinitionRef",
  GenericType = "GenericType",
  InterfaceRef = "InterfaceRef",
}

export const decodeType = (json: any): Type => {
  switch (json.type) {
    case TypeKind.DefinitionRef:
      return decodeDefinitionRef(json);
    case TypeKind.GenericType:
      return decodeGenericType(json);
    case TypeKind.InterfaceRef:
      return decodeInterfaceRef(json);
    default:
      throw Error("Decoding type " + json.type + " is not supported");
  }
};

export default interface Type {
  type: TypeKind;
}
