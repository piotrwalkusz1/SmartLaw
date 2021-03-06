import { TypeKind } from "./Type";
import { decodeString } from "../utils/Decoders";
import TypeTemplate from "./TypeTemplate";
import Template, { decodeTemplate } from "./Template";

export const decodeGenericTypeTemplate = (json: any): GenericTypeTemplate => {
  return {
    type: TypeKind.GenericType,
    name: decodeTemplate(json.name, decodeString),
  };
};

export default interface GenericTypeTemplate extends TypeTemplate {
  name: Template<string>;
}
