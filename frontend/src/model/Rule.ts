import Id, { decodeId } from "./Id";
import { List, Map } from "immutable";
import MetaArgument, { decodeMetaArgument } from "./MetaArgument";
import Template, { decodeTemplate } from "./Template";
import { decodeList, decodeMap, decodeNullable, decodeString } from "../utils/Decoders";

export const decodeRule = (json: any): Rule => {
  return {
    id: decodeId(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    arguments: decodeList(json.arguments, decodeMetaArgument),
    content: decodeTemplate(json.content),
    elements: decodeTemplate(json.elements),
    outputs: decodeMap(json.outputs, decodeTemplate),
    interfaces: decodeList(json.interfaces, decodeId),
  };
};

export default interface Rule {
  id: Id;
  name: string;
  description: string | null;
  arguments: List<MetaArgument>;
  content: Template;
  elements: Template;
  outputs: Map<String, Template>;
  interfaces: List<Id>;
}
