import { decodeEnum, decodeNullable, decodeNumber, decodeString } from "./Decoders";

type WrapWithMetaData<Type> = {
  [Property in keyof Type]: MetaData<Type[Property]>;
};

interface MetaData<T> {
  decodeOrException(json: any): T;

  create(): T;
}

interface InheritanceMetaData<T> extends MetaData<T> {
  createChild(type: string): T;
}

export const stringMeta = (): MetaData<string> => {
  return {
    decodeOrException(json: any): string {
      return decodeString(json);
    },
    create(): string {
      return "";
    },
  };
};

export const numberMeta = (): MetaData<number> => {
  return {
    decodeOrException(json: any): number {
      return decodeNumber(json);
    },
    create(): number {
      return 0;
    },
  };
};

export const enumMeta = <T>(enumObject: { [key: string]: T }): MetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return decodeEnum(json, enumObject);
    },
    create(): T {
      return enumObject[0];
    },
  };
};

export const nullableMeta = <T>(metaData: MetaData<T>): MetaData<T | null> => {
  return {
    decodeOrException(json: any): T | null {
      return decodeNullable(json, metaData.decodeOrException);
    },
    create(): T | null {
      return null;
    },
  };
};

export const buildMeta = <T>(metaData: WrapWithMetaData<T>): MetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return Object.fromEntries(
        Object.entries(metaData).map(([key, value]) => {
          return [key, (value as MetaData<any>).decodeOrException(json.key)];
        })
      ) as T;
    },
    create(): T {
      return Object.fromEntries(
        Object.entries(metaData).map(([key, value]) => {
          return [key, (value as MetaData<any>).create()];
        })
      ) as T;
    },
  };
};

export const buildInheritanceMeta = <T>(field: string, mapping: { [key: string]: MetaData<T> }): InheritanceMetaData<T> => {
  return {
    decodeOrException(json: any): T {
      return mapping[json[field]].decodeOrException(json);
    },
    create(): T {
      throw Error("Cannot create abstract type");
    },
    createChild(type: string): T {
      return mapping[type].create();
    },
  };
};
