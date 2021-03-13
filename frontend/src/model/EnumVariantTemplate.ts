import Type from "./Type";
import { decodeNullable, decodeString } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import EnumVariant from "./EnumVariant";
import { prepareStaticTemplate } from "./StaticTemplate";

export const decodeEnumVariantTemplate = (json: any): EnumVariantTemplate => {
  return {
    templateType: TemplateType.EnumVariantTemplate,
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
  };
};

export default interface EnumVariantTemplate extends Template<EnumVariant> {
  name: Template<string>;
  description: Template<string | null>;
}

export const prepareEmptyEnumVariantTemplate = (): EnumVariantTemplate => {
  return {
    templateType: TemplateType.EnumVariantTemplate,
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
  };
};

export const isEnumVariantTemplate = <T>(template: Template<Type>): template is EnumVariantTemplate => {
  return template.templateType === TemplateType.EnumVariantTemplate;
};
