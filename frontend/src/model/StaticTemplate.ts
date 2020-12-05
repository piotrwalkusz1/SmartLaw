import Template, { TemplateType } from "./Template";
import { decodeEnum } from "../utils/Decoders";

export const decodeStaticTemplate = (json: any): StaticTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    value: json.value,
  };
};

export default interface StaticTemplate extends Template {
  value: any;
}
