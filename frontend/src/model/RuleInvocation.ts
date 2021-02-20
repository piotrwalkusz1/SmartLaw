import Id, { decodeId } from "./Id";
import MetaValue, { decodeMetaValue } from "./MetaValue";
import { Map } from "immutable";
import { decodeMap } from "../utils/Decoders";

export const decodeRuleInvocation = (json: any): RuleInvocation => {
  return {
    ruleId: decodeId(json.ruleId),
    arguments: decodeMap(json.arguments, decodeMetaValue),
  };
};

export default interface RuleInvocation {
  ruleId: Id;
  arguments: Map<String, MetaValue>;
}
