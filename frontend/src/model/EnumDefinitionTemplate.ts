import ElementTemplate from "./ElementTemplate";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { decodeEnum, decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import { decodeId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyIdTemplate } from "./IdTemplate";
import { List } from "immutable";
import EnumVariant, { decodeEnumVariant } from "./EnumVariant";
import { prepareEmptyListTemplate } from "./ListTemplate";

export const decodeEnumDefinitionTemplate = (json: any): EnumDefinitionTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    id: decodeTemplate(json.id, decodeId),
    annotations: decodeTemplate(json.annotations, (json) => decodeList(json, decodeAnnotation)),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    variants: decodeTemplate(json.variants, (json) => decodeList(json, decodeEnumVariant)),
  };
};

export default interface EnumDefinitionTemplate extends ElementTemplate {
  name: Template<string>;
  description: Template<string | null>;
  variants: Template<List<EnumVariant>>;
}

export const prepareEmptyEnumDefinitionTemplate = (): EnumDefinitionTemplate => {
  return {
    templateType: TemplateType.EnumDefinitionTemplate,
    id: prepareEmptyIdTemplate(),
    annotations: prepareStaticTemplate(List()),
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    variants: prepareEmptyListTemplate(),
  };
};

export const isEnumDefinitionTemplate = <T>(template: Template<T>): template is EnumDefinitionTemplate => {
  return template.templateType === TemplateType.EnumDefinitionTemplate;
};
