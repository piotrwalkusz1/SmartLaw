import Template, { TemplateType } from "./Template";
import { decodeEnum, decodeString } from "../utils/Decoders";

export enum TextEngineType {
  FreeMarker = "FreeMarker",
}

export const decodeTextEngineTemplate = (json: any): TextEngineTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    type: decodeString(json.type),
    template: decodeString(json.template),
  };
};

export default interface TextEngineTemplate extends Template {
  type: String;
  template: String;
}
