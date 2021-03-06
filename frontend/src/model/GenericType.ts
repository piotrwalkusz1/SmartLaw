import Type, { TypeKind } from "./Type";
import { decodeString } from "../utils/Decoders";

export const decodeGenericType = (json: any): GenericType => {
  return {
    type: TypeKind.GenericType,
    name: decodeString(json.name),
  };
};

export const prepareGenericType = (name: string): GenericType => {
  return {
    type: TypeKind.GenericType,
    name: name,
  };
};

export default interface GenericType extends Type {
  name: string;
}
