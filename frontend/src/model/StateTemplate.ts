import ElementTemplate, { ElementTemplateType } from "./ElementTemplate";
import Template, { decodeTemplate } from "./Template";
import { decodeEnum, decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import { decodeId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import MetaValue, { decodeMetaValue } from "./MetaValue";
import TypeTemplate, { decodeTypeTemplate } from "./TypeTemplate";

export const decodeStateTemplate = (json: any): StateTemplate => {
  return {
    elementType: decodeEnum(json.elementType, ElementTemplateType),
    id: decodeTemplate(json.id, decodeId),
    annotations: decodeTemplate(json.annotations, (json) => decodeList(json, decodeAnnotation)),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeTypeTemplate),
    defaultValue: decodeTemplate(json.type, (json) => decodeNullable(json, decodeMetaValue)),
  };
};

export default interface StateTemplate extends ElementTemplate {
  name: Template<string>;
  description: Template<string | null>;
  type: Template<TypeTemplate>;
  defaultValue: Template<MetaValue | null>;
}
