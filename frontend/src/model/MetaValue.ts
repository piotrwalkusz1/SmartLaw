import { decodeMetaPrimitiveValue } from "./MetaPrimitiveValue";

export enum MetaValueType {
  Primitive = "Primitive",
}

export const decodeMetaValue = (json: any): MetaValue => {
  switch (json.type) {
    case MetaValueType.Primitive:
      return decodeMetaPrimitiveValue(json);
    default:
      throw Error("Meta value type " + json.type + " is not supported");
  }
};

export default interface MetaValue {
  type: MetaValueType;
}
