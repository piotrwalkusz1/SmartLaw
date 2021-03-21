import Template from "./Template";
import { decodeEnum } from "../utils/Decoders";
import { TemplateType } from "./TemplateType";

export default interface StaticTemplate<T> extends Template<T> {
  value: T;
}

export const decodeStaticTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): StaticTemplate<T> => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    value: decodeTemplateResult(json.value),
  };
};

export const prepareStaticTemplate = <T>(value: T): StaticTemplate<T> => {
  return {
    templateType: TemplateType.Static,
    value: value,
  };
};

export const prepareEmptyStaticTemplate = (): StaticTemplate<any> => {
  return {
    templateType: TemplateType.Static,
    value: "",
  };
};

export const isStaticTemplate = <T>(template: Template<T>): template is StaticTemplate<T> => {
  return template.templateType === TemplateType.Static;
};
