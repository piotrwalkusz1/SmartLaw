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
import { List } from "immutable";

export interface ModelUtils<T extends Template<R>, R> {
  metaData: MetaData<T>;
  templateType: TemplateType;
  decodeTemplate: (json: any) => Template<T>;
  createTemplate: () => Template<T>;
  isTemplate: (template: Template<R>) => template is T;
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
  renderTemplateEditor?: RenderTemplateEditor<Template<T>, T>
) => {
  const metaData = buildComplexDataMeta<T>(fields);
  const templateMetaData = buildTemplateMetaData(templateType, metaData.fields);
  metaData.templateMetaData = templateMetaData;
  metaData.templateType = templateType;
  metaData.renderTemplateEditor = renderTemplateEditor;
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

export const buildDerivativeModelUtilsWithTemplate = <T extends B, B, E, R extends Template<T>>(
  baseMetaData: BaseMetaData<B, E>,
  discriminatorValue: E,
  templateType: TemplateType,
  fields: MetaDataDerivativeFields<T, B>
) => {
  const metaData = buildDerivativeMetaData<T, B, E>(baseMetaData, discriminatorValue, fields);
  const templateMetaData = buildTemplateMetaData(templateType, metaData.fields);
  metaData.templateMetaData = templateMetaData;
  metaData.templateType = templateType;
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

export const getDerivativeMetaData = <T>(metaData: MetaData<T>): Array<MetaData<T>> => {
  const baseMetaData = metaData as BaseMetaData<T, any>;
  if (baseMetaData.derivatives) {
    return baseMetaData.derivatives;
  } else {
    return [metaData];
  }
};

export const getTemplateTypesFromMetaData = <T>(metaData: MetaData<T>): List<TemplateType> => {
  const derivativeMetaDataList = getDerivativeMetaData(metaData);
  const templateTypes = derivativeMetaDataList
    .filter((metaData) => metaData.templateType)
    .map((metaData) => metaData.templateType as TemplateType);

  return List(templateTypes);
};

export const getDerivativeMetaDataAndTemplateTypes = <T>(
  templateType: TemplateType,
  metaData: MetaData<T>
): { derivativeMetaData: MetaData<T> | undefined; templateTypes: List<TemplateType> } => {
  const derivativeMetaDataList = getDerivativeMetaData(metaData);
  let derivativeMetaData: MetaData<T> | undefined = undefined;
  if (derivativeMetaDataList.length > 1) {
    derivativeMetaData = derivativeMetaDataList.find((derivativeMetaData) => derivativeMetaData.templateType === templateType);
  } else if (derivativeMetaDataList.length === 1) {
    derivativeMetaData = derivativeMetaDataList[0];
  }
  const templateTypes = derivativeMetaDataList
    .filter((metaData) => metaData.templateType)
    .map((metaData) => metaData.templateType as TemplateType);

  return {
    derivativeMetaData: derivativeMetaData,
    templateTypes: List(templateTypes),
  };
};
