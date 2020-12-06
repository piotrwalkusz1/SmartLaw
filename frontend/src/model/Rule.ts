import Id, { decodeId } from "./Id";
import RuleInvocation, { decodeRuleInvocation } from "./RuleInvocation";
import { List } from "immutable";
import MetaArgument, { decodeMetaArgument } from "./MetaArgument";
import Template, { decodeTemplate } from "./Template";
import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Element, { decodeElement } from "./Element";

export const decodeRule = (json: any): Rule => {
  return {
    id: decodeId(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    content: decodeTemplate(json.content),
    arguments: decodeList(json.arguments, decodeMetaArgument),
    elements: decodeList(json.elements, decodeElement),
    ruleInvocations: decodeList(json.ruleInvocations, decodeRuleInvocation),
  };
};

export default interface Rule {
  id: Id;
  name: string;
  description: string | null;
  content: Template;
  arguments: List<MetaArgument>;
  elements: List<Element>;
  ruleInvocations: List<RuleInvocation>;
}
