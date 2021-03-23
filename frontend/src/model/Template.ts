import { decodeEnum } from "../utils/Decoders";
import {
  buildComplexDataMeta,
  enumMeta,
  getCreateTemplateByMetaData,
  MetaData,
  MetaDataFields,
  TemplateMetaData,
} from "../utils/Reflection";
import { decodeStaticTemplate, prepareEmptyStaticTemplate, prepareStaticTemplate } from "./StaticTemplate";
import { TemplateType } from "./TemplateType";
import { decodeGroovyTemplate, prepareEmptyGroovyTemplate } from "./GroovyTemplate";
import { decodeTextEngineTemplate, prepareEmptyTextEngineTemplate } from "./TextEngineTemplate";
import { decodeListTemplate, prepareEmptyListTemplate } from "./ListTemplate";
import { ModelUtils } from "../utils/ModelUtils";

export const MODELS_UTILS: Array<ModelUtils<any, any>> = [];

export const decodeTemplate = <T, R extends T>(json: any, decodeTemplateResult: (json: any) => R): Template<T> => {
  const templateType = decodeEnum(json.templateType, TemplateType);

  switch (templateType) {
    case TemplateType.Static:
      return decodeStaticTemplate(json, decodeTemplateResult);
    case TemplateType.ListTemplate:
      return decodeListTemplate(json, decodeTemplateResult);
    case TemplateType.TextEngine:
      return decodeTextEngineTemplate(json);
    case TemplateType.Groovy:
      return decodeGroovyTemplate(json);
    default:
      const model = MODELS_UTILS.find((model) => model.templateType === templateType);
      if (model) {
        return model.decodeTemplate(json);
      } else {
        throw Error("Cannot decode template type " + templateType);
      }
  }
};

export default interface Template<T> {
  templateType: TemplateType;
}

export const prepareEmptyTemplate = (templateType: TemplateType): Template<any> => {
  switch (templateType) {
    case TemplateType.Static:
      return prepareEmptyStaticTemplate();
    case TemplateType.ListTemplate:
      return prepareEmptyListTemplate();
    case TemplateType.TextEngine:
      return prepareEmptyTextEngineTemplate();
    case TemplateType.Groovy:
      return prepareEmptyGroovyTemplate();
    default:
      const model = MODELS_UTILS.find((model) => model.templateType === templateType);
      if (model) {
        return model.createTemplate();
      } else {
        throw Error("Cannot create empty template type " + templateType);
      }
  }
};

const nestedTemplateMeta = <T>(metaData: MetaData<T>): MetaData<Template<T>> => {
  return {
    decodeOrException(json: any): Template<T> {
      return decodeTemplate(json, metaData.decodeOrException);
    },
    create(): Template<T> {
      const create = getCreateTemplateByMetaData(metaData);
      if (create) {
        return create();
      } else {
        return prepareStaticTemplate(metaData.create());
      }
    },
  };
};

export const buildTemplateMetaData = <T>(templateType: TemplateType, baseFields: MetaDataFields<T>): TemplateMetaData<Template<T>> => {
  const fields: { [key: string]: MetaData<any> } = Object.fromEntries(
    Object.entries<MetaData<any>>(baseFields)
      .filter(([, value]) => !value.excludeFromTemplate)
      .map(([key, value]) => {
        return [key, nestedTemplateMeta(value)];
      })
  );
  fields.templateType = enumMeta(TemplateType, templateType);
  const templateFields = fields as MetaDataFields<Template<T>>;

  return { ...buildComplexDataMeta(templateFields), templateType };
};
