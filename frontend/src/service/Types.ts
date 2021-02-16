import Id from "../model/Id";

const typesIds = ["INTEGER", "STRING", "LOCAL_DATE"] as const;

type TypeId = typeof typesIds[number];

const TYPES: Record<TypeId, Id> = {
  INTEGER: { id: "Integer", namespace: null },
  STRING: { id: "String", namespace: null },
  LOCAL_DATE: { id: "LocalDate", namespace: null },
} as const;

export const RULE_ARGUMENT_TYPES = [TYPES.INTEGER, TYPES.STRING, TYPES.LOCAL_DATE] as const;

export const STATE_ELEMENT_VALUE_TYPES = [TYPES.INTEGER, TYPES.STRING, TYPES.LOCAL_DATE] as const;
