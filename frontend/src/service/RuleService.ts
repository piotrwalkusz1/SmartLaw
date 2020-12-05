import axios from "axios";
import Id from "../model/Id";
import Rule, { decodeRule } from "../model/Rule";

export const getRule = (ruleId: Id, projectId: string): Promise<Rule> => {
  return axios.post("/rules/search", { ruleId: ruleId, projectId: projectId }).then((response) => {
    return decodeRule(response.data);
  });
};
