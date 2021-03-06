import { decodeDefinitionRef } from "./DefinitionRef";
import { decodeInterfaceRef } from "./InterfaceRef";
import { decodeGenericType } from "./GenericType";
import { TypeKind } from "./Type";

export const decodeTypeTemplate = (json: any): TypeTemplate => {
  switch (json.type) {
    case TypeKind.DefinitionRef:
      return decodeDefinitionRef(json);
    case TypeKind.GenericType:
      return decodeGenericType(json);
    case TypeKind.InterfaceRef:
      return decodeInterfaceRef(json);
    default:
      throw Error("Decoding type template " + json.type + " is not supported");
  }
};

export default interface TypeTemplate {
  type: TypeKind;
}
