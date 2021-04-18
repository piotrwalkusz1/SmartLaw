import Template, { decodeTemplate } from "./Template";
import { Map } from "immutable";
import { decodeEnum, decodeMap } from "../utils/Decoders";
import { TemplateType } from "./TemplateType";

export default interface MapTemplate<T> extends Template<Map<string, T>> {
  items: Map<string, Template<T>>;
}

export const decodeMapTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): MapTemplate<T> => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    items: decodeMap(json.items, (json) => decodeTemplate(json, decodeTemplateResult)),
  };
};

export const prepareEmptyMapTemplate = <T>(): MapTemplate<T> => {
  return {
    templateType: TemplateType.MapTemplate,
    items: Map(),
  };
};

export const isMapTemplate = <T>(template: Template<Map<string, T>>): template is MapTemplate<T> => {
  return template.templateType === TemplateType.MapTemplate;
};
