import Type, { TypeKind } from "./Type";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";
import TypeTemplate, { decodeTypeTemplate } from "./TypeTemplate";
import Template, { decodeTemplate } from "./Template";

export const decodeDefinitionRef = (json: any): DefinitionRefTemplate => {
  return {
    type: TypeKind.DefinitionRef,
    definition: decodeTemplate(json.definition, decodeId),
    parameters: decodeTemplate(json.parameters, (json) => decodeList(json, decodeTypeTemplate)),
  };
};

export default interface DefinitionRefTemplate extends Type {
  definition: Template<Id>;
  parameters: Template<List<TypeTemplate>>;
}
