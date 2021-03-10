import Template, { TemplateType } from "./Template";
import { decodeEnum, decodeString } from "../utils/Decoders";

export const decodeGroovyTemplate = <T>(json: any): GroovyTemplate<T> => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    script: decodeString(json.script),
  };
};

export const isGroovyTemplate = <T>(template: Template<T>): template is GroovyTemplate<T> => {
  return template.templateType === TemplateType.Groovy;
};

export default interface GroovyTemplate<T> extends Template<T> {
  script: string;
}

export const prepareEmptyGroovyTemplate = (): GroovyTemplate<any> => {
  return {
    templateType: TemplateType.Groovy,
    script: "",
  };
};
