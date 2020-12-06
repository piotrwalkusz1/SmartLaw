import axios from "axios";
import Id from "../model/Id";
import Rule, { decodeRule } from "../model/Rule";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";

export const searchRules = (request: { searchPhrase?: string; ruleId?: Id; projectId: string }): Promise<List<Rule>> => {
  return axios.post("/rules/search", request).then((response) => {
    return decodeList(response.data, decodeRule);
  });
};
