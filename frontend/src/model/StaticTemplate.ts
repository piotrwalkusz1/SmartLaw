import Template, { TemplateType } from "./Template";

export const decodeStaticTemplate = (json: any): StaticTemplate => {
  return {
    templateType: TemplateType.Static,
    value: json.value,
  };
};

export default interface StaticTemplate extends Template {
  value: any;
}

export const prepareStaticTemplate = (value: any): StaticTemplate => {
  return {
    templateType: TemplateType.Static,
    value: value,
  };
};
