import { List } from "immutable";
import Document from "./Document";
import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Id, { decodeId } from "./Id";
import Rule, { decodeRule } from "./Rule";
import AnnotationType, { decodeAnnotationType } from "./AnnotationType";
import Element, { decodeElement } from "./Element";
import RuleInterface, { decodeRuleInterface } from "./RuleInterface";

export const decodeLibrary = (json: any): Library => {
  return new Library({
    documentType: decodeString(json.documentType),
    id: decodeId(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    rules: decodeList(json.rules, decodeRule),
    rulesInterfaces: decodeList(json.rulesInterfaces, decodeRuleInterface),
    elements: decodeList(json.elements, decodeElement),
    annotations: decodeList(json.annotations, decodeAnnotationType),
  });
};

export default class Library implements Document {
  documentType: string;
  id: Id;
  name: string;
  description: string | null;
  rules: List<Rule>;
  rulesInterfaces: List<RuleInterface>;
  elements: List<Element>;
  annotations: List<AnnotationType>;

  constructor({
    documentType,
    id,
    name,
    description,
    rules,
    rulesInterfaces,
    elements,
    annotations,
  }: {
    documentType: string;
    id: Id;
    name: string;
    description: string | null;
    rules: List<Rule>;
    rulesInterfaces: List<RuleInterface>;
    elements: List<Element>;
    annotations: List<AnnotationType>;
  }) {
    this.documentType = documentType;
    this.id = id;
    this.name = name;
    this.description = description;
    this.rules = rules;
    this.rulesInterfaces = rulesInterfaces;
    this.elements = elements;
    this.annotations = annotations;
  }

  withRule(index: number, rule: Rule): Library {
    return this.withRules(this.rules.set(index, rule));
  }

  withRules(rules: List<Rule>): Library {
    return new Library({ ...this, rules: rules });
  }

  withRulesInterfaces(rulesInterfaces: List<RuleInterface>): Library {
    return new Library({ ...this, rulesInterfaces: rulesInterfaces });
  }
}
