import Type, { TypeKind } from "./Type";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";
import Template, { decodeTemplate } from "./Template";
import TypeTemplate, { decodeTypeTemplate } from "./TypeTemplate";

export const decodeInterfaceRef = (json: any): InterfaceRefTemplate => {
  return {
    type: TypeKind.InterfaceRef,
    interfaceId: decodeTemplate(json.interfaceId, decodeId),
    parameters: decodeTemplate(json.parameters, (json) => decodeList(json, decodeTypeTemplate)),
  };
};

export default interface InterfaceRefTemplate extends Type {
  interfaceId: Template<Id>;
  parameters: Template<List<TypeTemplate>>;
}
