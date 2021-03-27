import Id from "../model/Id";

const metaTypesIds = ["INTEGER", "STRING", "LOCAL_DATE"] as const;

type MetaTypeId = typeof metaTypesIds[number];

export const META_TYPES: Record<MetaTypeId, Id> = {
  INTEGER: { id: "Integer", namespace: "" },
  STRING: { id: "String", namespace: "" },
  LOCAL_DATE: { id: "LocalDate", namespace: "" },
} as const;

export const STATE_ELEMENT_VALUE_TYPES = [META_TYPES.INTEGER, META_TYPES.STRING, META_TYPES.LOCAL_DATE] as const;
