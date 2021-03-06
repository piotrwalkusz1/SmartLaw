import Type, { decodeType, TypeKind } from "./Type";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";

export const decodeInterfaceRef = (json: any): InterfaceRef => {
  return {
    type: TypeKind.InterfaceRef,
    interfaceId: decodeId(json.interfaceId),
    parameters: decodeList(json.parameters, decodeType),
  };
};

export const prepareInterfaceRef = (interfaceId: Id): InterfaceRef => {
  return {
    type: TypeKind.DefinitionRef,
    interfaceId: interfaceId,
    parameters: List(),
  };
};

export default interface InterfaceRef extends Type {
  interfaceId: Id;
  parameters: List<Type>;
}
