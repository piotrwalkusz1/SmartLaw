import MetaValue, { MetaValueType } from "./MetaValue";
import { decodeEnum, decodeString } from "../utils/Decoders";

export const decodeMetaPrimitiveValue = (json: any): MetaPrimitiveValue => {
  return {
    type: decodeEnum(json.type, MetaValueType),
    value: decodeString(json.value),
  };
};

export default interface MetaPrimitiveValue extends MetaValue {
  value: string;
}
