import ElementTemplate, { ElementTemplateType } from "./ElementTemplate";
import Template, { decodeTemplate } from "./Template";
import { decodeEnum } from "../utils/Decoders";

export const decodeStateTemplate = (json: any): StateTemplate => {
  return {
    elementType: decodeEnum(json.elementType, ElementTemplateType),
    id: decodeTemplate(json.id),
    annotations: decodeTemplate(json.annotations),
    name: decodeTemplate(json.name),
    description: decodeTemplate(json.description),
    type: decodeTemplate(json.type),
    defaultValue: decodeTemplate(json.type),
  };
};

export default interface StateTemplate extends ElementTemplate {
  name: Template;
  description: Template;
  type: Template;
  defaultValue: Template;
}
