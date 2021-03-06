import Template, { TemplateType } from "./Template";
import { decodeEnum, decodeString } from "../utils/Decoders";

export enum TextEngineType {
  FreeMarker = "FreeMarker",
}

export const decodeTextEngineTemplate = <T>(json: any): TextEngineTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    type: decodeString(json.type),
    template: decodeString(json.template),
  };
};

export const isTextEngineTemplate = (template: Template<string>): template is TextEngineTemplate => {
  return template.templateType === TemplateType.TextEngine;
};

export default interface TextEngineTemplate extends Template<string> {
  type: String;
  template: String;
}
