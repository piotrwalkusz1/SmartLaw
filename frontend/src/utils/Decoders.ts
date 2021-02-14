import { List, Map } from "immutable";

export const decodeNullable = <T extends {}>(json: any, decoder: (value: any) => T): T | null => {
  if (json === undefined || json === null) {
    return null;
  } else {
    return decoder(json);
  }
};

export const decodeString = (json: any): string => {
  if (typeof json === "string") {
    return json;
  } else {
    throw Error("String expected but was " + json);
  }
};

export const decodeNumber = (json: any): Number => {
  if (typeof json === "number") {
    return json;
  } else {
    throw Error("Number expected but was " + json);
  }
};

export const decodeBoolean = (json: any): Boolean => {
  if (typeof json === "boolean") {
    return json;
  } else {
    throw Error("Boolean expected but was " + json);
  }
};

export const decodeList = <T extends {}>(json: any, mapper: (value: any) => T): List<T> => {
  if (Array.isArray(json)) {
    return List(json.map(mapper));
  } else {
    throw Error("Array expected but was " + json);
  }
};

export const decodeMap = <T extends {}>(json: any, mapper: (value: any) => T): Map<String, T> => {
  if (typeof json === "object") {
    return Map(Object.entries(json).map(([key, value]) => [key, mapper(value)]));
  } else {
    throw Error("Object expected but was " + json);
  }
};

export const decodeEnum = <T>(json: any, enumObject: { [key: string]: T }): T => {
  const value = decodeString(json);

  if (Object.values(enumObject).includes((value as unknown) as T)) {
    return (value as unknown) as T;
  } else {
    throw Error("Enum " + enumObject + " expected but " + value + " was");
  }
};
