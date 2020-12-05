import Id, { decodeId } from "./Id";
import MetaValue, { decodeMetaValue } from "./MetaValue";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";

export const decodeRuleInvocation = (json: any): RuleInvocation => {
  return {
    ruleId: decodeId(json.ruleId),
    arguments: decodeList(json.arguments, decodeMetaValue),
  };
};

export default interface RuleInvocation {
  ruleId: Id;
  arguments: List<MetaValue>;
}
