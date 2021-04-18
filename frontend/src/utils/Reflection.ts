import { decodeEnum, decodeList, decodeMap, decodeNullable, decodeNumber, decodeString } from "./Decoders";
import { List, Map } from "immutable";
import Template, { decodeTemplate } from "../model/Template";
import { TemplateType } from "../model/TemplateType";
import { ReactElement } from "react";
import ListTemplateEditor from "../component/element/template/ListTemplateEditor";
import StringTemplateEditor from "../component/element/template/StringTemplateEditor";
import { decodeListTemplate, prepareEmptyListTemplate } from "../model/ListTemplate";
import NullableTemplate from "../component/template/NullableTemplate";
import { decodeStaticTemplate, isStaticTemplate, prepareStaticTemplate } from "../model/StaticTemplate";
import MapTemplateEditor from "../component/element/template/MapTemplateEditor";
import { decodeMapTemplate, prepareEmptyMapTemplate } from "../model/MapTemplate";

export type RenderTemplateEditor<T extends Template<R>, R> = (
  template: T,
  onTemplateChange: (template: T) => void,
  fieldName?: string
) => ReactElement;

export type MetaDataFields<Type> = {
  [Property in keyof Type]: MetaData<Type[Property]>;
};

export type MetaDataDerivativeFields<Derivative, Base> = {
  [Property in keyof Derivative as Exclude<Property, keyof Base>]: MetaData<Derivative[Property]>;
};

const mergeBaseAndDerivativeFields = <T, B>(
  baseFields: MetaDataFields<B>,
  derivativeFields: MetaDataDerivativeFields<T, B>
): MetaDataFields<T> => {
  return ({ ...baseFields, ...derivativeFields } as unknown) as MetaDataFields<T>;
};

export interface MetaData<T> {
  excludeFromTemplate?: boolean;
  templateType?: TemplateType;
  renderTemplateEditor?: RenderTemplateEditor<Template<T>, T>;
  templateMetaData?: MetaData<Template<T>>;

  decodeOrException(json: any): T;

  create(): T;
}

export interface ComplexMetaData<T> extends MetaData<T> {
  fields: MetaDataFields<T>;
}

export interface TemplateMetaData<T extends Template<any>> extends ComplexMetaData<T> {
  templateType: TemplateType;
}

export interface BaseMetaData<T, E> extends ComplexMetaData<T> {
  discriminator: keyof T;
  baseMetaData: BaseMetaData<any, E> | null;
  derivatives: Array<DerivativeMetaData<any, T, E>>;
}

export interface DerivativeMetaData<T extends B, B, E> extends ComplexMetaData<T> {
  baseMetaData: BaseMetaData<B, E>;
  discriminatorValue: E;
}

export const withDefaultValue = <T>(metaData: MetaData<T>, defaultValue: T): MetaData<T> => {
  return { ...metaData, create: () => defaultValue };
};

export const stringMeta: MetaData<string> = {
  decodeOrException(json: any): string {
    return decodeString(json);
  },
  create(): string {
    return "";
  },
  renderTemplateEditor: (template, onChange, fieldName) => {
    return StringTemplateEditor({ template, onChange, label: fieldName });
  },
  templateMetaData: {
    decodeOrException(json: any): Template<string> {
      return decodeTemplate(json, decodeString);
    },
    create(): Template<string> {
      return prepareStaticTemplate("");
    },
  },
};

export const numberMeta: MetaData<number> = {
  decodeOrException(json: any): number {
    return decodeNumber(json);
  },
  create(): number {
    return 0;
  },
  templateMetaData: {
    decodeOrException(json: any): Template<number> {
      return decodeTemplate(json, decodeNumber);
    },
    create(): Template<number> {
      return prepareStaticTemplate(0);
    },
  },
};

export const enumMeta = <T>(enumObject: { [key: string]: T }, defaultValue?: T): MetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return decodeEnum(json, enumObject);
    },
    create(): T {
      return defaultValue || enumObject[0];
    },
  };
};

export const nullableMeta = <T>(metaData: MetaData<T>): MetaData<T | null> => {
  const templateMetaData = getTemplateMetaDataByMetaData(metaData);
  const decodeOrException = (json: any): T | null => decodeNullable(json, metaData.decodeOrException);

  return {
    decodeOrException: decodeOrException,
    create(): T | null {
      return null;
    },
    renderTemplateEditor: (template, onTemplateChange, fieldName) => {
      return NullableTemplate({ template, onTemplateChange, fieldName, metaData });
    },
    templateMetaData: templateMetaData && {
      decodeOrException(json: any): Template<T | null> {
        if (isStaticTemplate(json as Template<T>)) {
          return decodeStaticTemplate(json, decodeOrException);
        } else {
          return templateMetaData.decodeOrException(json);
        }
      },
      create(): Template<T | null> {
        return prepareStaticTemplate(null);
      },
    },
  };
};

export const listMeta = <T>(metaData: MetaData<T>): MetaData<List<T>> => {
  return {
    decodeOrException(json: any): List<T> {
      return decodeList(json, metaData.decodeOrException);
    },
    create(): List<T> {
      return List();
    },
    templateType: TemplateType.ListTemplate,
    renderTemplateEditor: (template, onChange, fieldName) => {
      return ListTemplateEditor<T>({ template, onChange, metaData, header: fieldName });
    },
    templateMetaData: {
      decodeOrException(json: any): Template<List<T>> {
        return decodeListTemplate(json, metaData.decodeOrException);
      },
      create(): Template<List<T>> {
        return prepareEmptyListTemplate();
      },
    },
  };
};

export const mapMeta = <T>(metaData: MetaData<T>): MetaData<Map<string, T>> => {
  return {
    decodeOrException(json: any): Map<string, T> {
      return decodeMap(json, metaData.decodeOrException);
    },
    create(): Map<string, T> {
      return Map();
    },
    templateType: TemplateType.MapTemplate,
    renderTemplateEditor: (template, onChange, fieldName) => {
      return MapTemplateEditor<T>({ template, onChange, metaData, header: fieldName });
    },
    templateMetaData: {
      decodeOrException(json: any): Template<Map<string, T>> {
        return decodeMapTemplate(json, metaData.decodeOrException);
      },
      create(): Template<Map<string, T>> {
        return prepareEmptyMapTemplate();
      },
    },
  };
};

export const identityMeta = <T>(): MetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return json;
    },
    create(): T {
      throw Error("Cannot create empty static template");
    },
  };
};

export const excludeFromTemplate = <T>(metaData: MetaData<T>): MetaData<T> => {
  return { ...metaData, excludeFromTemplate: true };
};

const decodeOrException = <T>(json: any, fields: MetaDataFields<T>): T => {
  return Object.fromEntries(
    Object.entries(fields).map(([key, value]) => {
      return [key, (value as MetaData<any>).decodeOrException(json[key])];
    })
  ) as T;
};

const create = <T>(fields: MetaDataFields<T>): T => {
  return Object.fromEntries(
    Object.entries(fields).map(([key, value]) => {
      return [key, (value as MetaData<any>).create()];
    })
  ) as T;
};

export const buildComplexDataMeta = <T>(fields: MetaDataFields<T>): ComplexMetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return decodeOrException(json, fields);
    },
    create(): T {
      return create(fields);
    },
    fields: fields,
  };
};

export const buildBaseMetaData = <T, E>(
  discriminator: keyof T,
  discriminatorType: { [key: string]: E },
  baseMetaData: BaseMetaData<any, E> | null,
  fields: MetaDataFields<T>
): BaseMetaData<T, E> => {
  const derivatives: Array<DerivativeMetaData<any, T, E>> = [];

  return {
    baseMetaData: baseMetaData,
    derivatives: derivatives,
    decodeOrException(json: any): T {
      const discriminatorValue = decodeEnum(json[discriminator], discriminatorType);
      const derivative = derivatives.find((derivative) => derivative.discriminatorValue === discriminatorValue);
      if (derivative) {
        return derivative.decodeOrException(json);
      } else {
        throw Error('Child with type "' + discriminatorValue + '" is not known');
      }
    },
    create(): T {
      const derivative = derivatives[0];
      if (derivative) {
        return derivative.create();
      } else {
        throw Error("This abstract class has no concrete type.");
      }
    },
    fields: fields,
    discriminator: discriminator,
  };
};

const addDerivativeToBaseMetaData = <T extends B, B, E>(baseMetaData: BaseMetaData<B, E>, derivative: DerivativeMetaData<T, B, E>) => {
  let base: BaseMetaData<any, E> | null = baseMetaData;
  do {
    base.derivatives.push(derivative);
    base = base.baseMetaData;
  } while (base !== null);
};

export const buildDerivativeMetaData = <T extends B, B, E>(
  baseMetaData: BaseMetaData<B, E>,
  discriminatorValue: E,
  derivativeFields: MetaDataDerivativeFields<T, B>
): DerivativeMetaData<T, B, E> => {
  const fields = mergeBaseAndDerivativeFields(baseMetaData.fields, derivativeFields);
  const derivative = {
    create(): T {
      const model = create(fields);
      model[baseMetaData.discriminator] = discriminatorValue as any;
      return model;
    },
    decodeOrException(json: any): T {
      return decodeOrException(json, fields);
    },
    fields: fields,
    baseMetaData: baseMetaData,
    discriminatorValue: discriminatorValue,
  };
  addDerivativeToBaseMetaData(baseMetaData, derivative);

  return derivative;
};

export const getTemplateMetaDataByMetaData = <T>(metaData: MetaData<T>): MetaData<Template<T>> | undefined => {
  if (metaData.templateMetaData) {
    return metaData.templateMetaData;
  }
  const baseMetaData = metaData as BaseMetaData<T, any>;
  if (baseMetaData.derivatives && baseMetaData.derivatives.length > 0) {
    return baseMetaData.derivatives[0].templateMetaData;
  }
  return undefined;
};

export const getCreateTemplateByMetaData = <T>(metaData: MetaData<T>): (() => Template<T>) | undefined => {
  return getTemplateMetaDataByMetaData(metaData)?.create;
};

export const createTemplateByMetaData = <T>(metaData: MetaData<T>): Template<T> | undefined => {
  const create = getCreateTemplateByMetaData(metaData);
  return create && create();
};
