import Template, { buildTemplateMetaData, MODELS_UTILS } from "../model/Template";
import {
  BaseMetaData,
  buildComplexDataMeta,
  buildDerivativeMetaData,
  MetaData,
  MetaDataDerivativeFields,
  MetaDataFields,
  RenderTemplateEditor,
} from "./Reflection";
import { TemplateType } from "../model/TemplateType";

export interface ModelUtils<T extends Template<R>, R> {
  metaData: MetaData<R>;
  templateType: TemplateType;
  decodeTemplate: (json: any) => Template<T>;
  createTemplate: () => Template<T>;
  isTemplate: (template: Template<R>) => template is T;
  renderTemplateEditor?: RenderTemplateEditor<T, R>;
}

export const buildModelUtils = <T>(fields: MetaDataFields<T>) => {
  const metaData = buildComplexDataMeta<T>(fields);

  return {
    metaData: metaData,
    decode: metaData.decodeOrException,
    create: metaData.create,
  };
};

export const buildModelUtilsWithTemplate = <T, R extends Template<T>>(
  templateType: TemplateType,
  fields: MetaDataFields<T>,
  renderTemplateEditor?: RenderTemplateEditor<R, T>
) => {
  const metaData = buildComplexDataMeta<T>(fields);
  const templateMetaData = buildTemplateMetaData(templateType, metaData.fields);
  const modelUtils = {
    metaData: metaData,
    decode: metaData.decodeOrException,
    create: metaData.create,
    templateMetaData: templateMetaData,
    decodeTemplate: templateMetaData.decodeOrException,
    createTemplate: templateMetaData.create,
    templateType: templateType,
    isTemplate: <T>(template: Template<T>): template is R => {
      return template.templateType === templateType;
    },
    renderTemplateEditor: renderTemplateEditor,
  };
  MODELS_UTILS.push(modelUtils);

  return modelUtils;
};

export const buildDerivativeModelUtilsWithTemplate = <T extends B, B, E, R extends Template<T>>(
  baseMetaData: BaseMetaData<B, E>,
  discriminatorValue: E,
  templateType: TemplateType,
  fields: MetaDataDerivativeFields<T, B>
) => {
  const metaData = buildDerivativeMetaData<T, B, E>(baseMetaData, discriminatorValue, fields);
  const templateMetaData = buildTemplateMetaData(templateType, metaData.fields);
  const modelUtils = {
    metaData: metaData,
    decode: metaData.decodeOrException,
    create: metaData.create,
    templateMetaData: templateMetaData,
    decodeTemplate: templateMetaData.decodeOrException,
    createTemplate: templateMetaData.create,
    templateType: templateType,
    isTemplate: <T>(template: Template<T>): template is R => {
      return template.templateType === templateType;
    },
  };
  MODELS_UTILS.push(modelUtils);

  return modelUtils;
};

export const getDerivativeModelsUtils = <T extends Template<R>, R>(modelUtils: ModelUtils<T, R>): Array<ModelUtils<any, any>> => {
  const baseMetaData = modelUtils.metaData as BaseMetaData<R, any>;
  if (baseMetaData.derivatives) {
    return MODELS_UTILS.filter((modelUtils) => baseMetaData.derivatives.includes(modelUtils.metaData as any));
  } else {
    return [modelUtils];
  }
};
