import Template, { TemplateType } from "./Template";
import { decodeEnum, decodeString } from "../utils/Decoders";

export const decodeGroovyTemplate = (json: any): GroovyTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    script: decodeString(json.script),
  };
};

export default interface GroovyTemplate extends Template {
  script: string;
}
