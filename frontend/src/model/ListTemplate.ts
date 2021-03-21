import Template, { decodeTemplate } from "./Template";
import { List } from "immutable";
import { decodeEnum, decodeList } from "../utils/Decoders";
import { TemplateType } from "./TemplateType";

export default interface ListTemplate<T extends Template<R>, R> extends Template<List<R>> {
  items: List<T>;
}

export const decodeListTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): ListTemplate<Template<T>, T> => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    items: decodeList(json.items, (json) => decodeTemplate(json, decodeTemplateResult)),
  };
};

export const prepareEmptyListTemplate = <T extends Template<R>, R>(): ListTemplate<T, R> => {
  return {
    templateType: TemplateType.ListTemplate,
    items: List(),
  };
};

export const isListTemplate = <T>(template: Template<List<T>>): template is ListTemplate<Template<T>, T> => {
  return template.templateType === TemplateType.ListTemplate;
};
