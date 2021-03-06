import Id from "../model/Id";

const metaTypesIds = ["INTEGER", "STRING", "LOCAL_DATE"] as const;

type MetaTypeId = typeof metaTypesIds[number];

export const META_TYPES: Record<MetaTypeId, Id> = {
  INTEGER: { id: "Integer", namespace: null },
  STRING: { id: "String", namespace: null },
  LOCAL_DATE: { id: "LocalDate", namespace: null },
} as const;

export const STATE_ELEMENT_VALUE_TYPES = [META_TYPES.INTEGER, META_TYPES.STRING, META_TYPES.LOCAL_DATE] as const;
