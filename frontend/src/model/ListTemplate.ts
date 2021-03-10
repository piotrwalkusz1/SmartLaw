import Template, { decodeTemplate, TemplateType } from "./Template";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";

export const decodeListTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): ListTemplate<Template<T>, T> => {
  return {
    templateType: TemplateType.ListTemplate,
    items: decodeList(json.items, (json) => decodeTemplate(json, decodeTemplateResult)),
  };
};

export default interface ListTemplate<T extends Template<R>, R> extends Template<List<R>> {
  items: List<T>;
}

export const isListTemplate = <T>(template: Template<List<T>>): template is ListTemplate<Template<T>, T> => {
  return template.templateType === TemplateType.ListTemplate;
};

export const prepareEmptyListTemplate = (): ListTemplate<any, any> => {
  return {
    templateType: TemplateType.ListTemplate,
    items: List(),
  };
};
