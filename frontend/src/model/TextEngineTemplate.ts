import Template from "./Template";
import { TemplateType } from "./TemplateType";
import { decodeEnum, decodeString } from "../utils/Decoders";

export enum TextEngineType {
  FreeMarker = "FreeMarker",
}

export default interface TextEngineTemplate extends Template<string> {
  type: string;
  template: string;
}

export const prepareEmptyTextEngineTemplate = (): TextEngineTemplate => {
  return prepareTextEngineTemplate(TextEngineType.FreeMarker, "");
};

export const prepareTextEngineTemplate = (type: TextEngineType, template: string): TextEngineTemplate => {
  return {
    templateType: TemplateType.TextEngine,
    type,
    template,
  };
};

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
