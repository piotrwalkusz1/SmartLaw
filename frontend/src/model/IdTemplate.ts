import { decodeNullable, decodeString } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import Id from "./Id";
import { prepareStaticTemplate } from "./StaticTemplate";

export const decodeIdTemplate = (json: any): IdTemplate => {
  return {
    templateType: TemplateType.IdTemplate,
    id: decodeTemplate(json.id, decodeString),
    namespace: decodeTemplate(json.namespace, (json) => decodeNullable(json, decodeString)),
  };
};

export default interface IdTemplate extends Template<Id> {
  id: Template<string>;
  namespace: Template<string | null>;
}

export const prepareEmptyIdTemplate = (): IdTemplate => {
  return {
    templateType: TemplateType.IdTemplate,
    id: prepareStaticTemplate(""),
    namespace: prepareStaticTemplate(null),
  };
};

export const isIdTemplate = <T>(template: Template<Id>): template is IdTemplate => {
  return template.templateType === TemplateType.IdTemplate;
};
