import Id, { IdUtils } from "./Id";
import { List, Map } from "immutable";
import MetaArgument, { decodeMetaArgument } from "./MetaArgument";
import Template, { decodeTemplate } from "./Template";
import { decodeList, decodeMap, decodeNullable, decodeString } from "../utils/Decoders";
import Element, { elementMeta } from "./Element";

export const decodeRule = (json: any): Rule => {
  return {
    id: IdUtils.decode(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    arguments: decodeList(json.arguments, decodeMetaArgument),
    content: decodeTemplate(json.content, decodeString),
    elements: decodeTemplate(json.elements, (json) => decodeList(json, elementMeta.decodeOrException)),
    outputs: decodeMap(json.outputs, (json) => decodeTemplate(json, (json) => json)),
    interfaces: decodeList(json.interfaces, IdUtils.decode),
  };
};

export default interface Rule {
  id: Id;
  name: string;
  description: string | null;
  arguments: List<MetaArgument>;
  content: Template<string>;
  elements: Template<List<Element>>;
  outputs: Map<string, Template<any>>;
  interfaces: List<Id>;
}
