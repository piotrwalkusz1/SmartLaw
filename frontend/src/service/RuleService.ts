import axios from "axios";
import Rule, { decodeRule } from "../model/Rule";
import { List, Map } from "immutable";
import { decodeList } from "../utils/Decoders";
import MetaValue, { MetaValueType } from "../model/MetaValue";
import MetaPrimitiveValue from "../model/MetaPrimitiveValue";
import MetaArgument from "../model/MetaArgument";
import { META_TYPES } from "./MetaTypes";
import MetaRuleValue from "../model/MetaRuleValue";
import Id, { IdUtils } from "../model/Id";
import RuleInterface, { RuleInterfaceUtils } from "../model/RuleInterface";

export const searchRules = (request: {
  searchPhrase?: string;
  ruleId?: Id;
  ruleInterfaceId?: Id;
  projectId: string;
}): Promise<List<Rule>> => {
  return axios.post("/rules/search", request).then((response) => {
    return decodeList(response.data, decodeRule);
  });
};

export const searchRulesInterfaces = (request: {
  searchPhrase?: string;
  ruleInterfaceId?: Id;
  projectId: string;
}): Promise<List<RuleInterface>> => {
  return axios.post("/rules/interfaces/search", request).then((response) => {
    return decodeList(response.data, RuleInterfaceUtils.decode);
  });
};

export const getRulesArgumentsTypes = (request: { projectId: string }): Promise<List<Id>> => {
  return axios.post("/rules/arguments/types", request).then((response) => {
    return decodeList(response.data, IdUtils.decode);
  });
};

export const prepareEmptyRuleInvocationArgument = (argument: MetaArgument): MetaValue => {
  if ([META_TYPES.STRING.id, META_TYPES.INTEGER.id, META_TYPES.LOCAL_DATE.id].includes(argument.type.id)) {
    return {
      type: MetaValueType.Primitive,
      value: "",
    } as MetaPrimitiveValue;
  } else {
    return {
      type: MetaValueType.Rule,
      ruleInvocation: {
        ruleId: IdUtils.create(),
        arguments: Map(),
      },
    } as MetaRuleValue;
  }
};
