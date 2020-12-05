import Element, { ElementType } from "./Element";
import Template, { decodeTemplate } from "./Template";
import { decodeEnum } from "../utils/Decoders";

export const decodeState = (json: any): State => {
  return {
    elementType: decodeEnum(json.elementType, ElementType),
    id: decodeTemplate(json.id),
    annotations: decodeTemplate(json.annotations),
    name: decodeTemplate(json.name),
    description: decodeTemplate(json.description),
    type: decodeTemplate(json.type),
    defaultValue: decodeTemplate(json.type),
  };
};

export default interface State extends Element {
  name: Template;
  description: Template;
  type: Template;
  defaultValue: Template;
}
