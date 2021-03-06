import Template, { TemplateType } from "./Template";

export const decodeStaticTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): StaticTemplate<T> => {
  return {
    templateType: TemplateType.Static,
    value: decodeTemplateResult(json.value),
  };
};

export default interface StaticTemplate<T> extends Template<T> {
  value: T;
}

export const isStaticTemplate = <T>(template: Template<T>): template is StaticTemplate<T> => {
  return template.templateType === TemplateType.Static;
};

export const prepareStaticTemplate = <T>(value: T): StaticTemplate<T> => {
  return {
    templateType: TemplateType.Static,
    value: value,
  };
};
