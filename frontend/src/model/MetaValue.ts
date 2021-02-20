import { decodeMetaPrimitiveValue } from "./MetaPrimitiveValue";
import { decodeMetaRuleValue } from "./MetaRuleValue";

export enum MetaValueType {
  Primitive = "Primitive",
  Rule = "Rule",
}

export const decodeMetaValue = (json: any): MetaValue => {
  switch (json.type) {
    case MetaValueType.Primitive:
      return decodeMetaPrimitiveValue(json);
    case MetaValueType.Rule:
      return decodeMetaRuleValue(json);
    default:
      throw Error("Meta value type " + json.type + " is not supported");
  }
};

export default interface MetaValue {
  type: MetaValueType;
}
