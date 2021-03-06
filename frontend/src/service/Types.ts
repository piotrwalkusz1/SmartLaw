import Id from "../model/Id";

const typesIds = ["UINT", "ADDRESS"] as const;

type TypeId = typeof typesIds[number];

export const TYPES: Record<TypeId, Id> = {
  UINT: { id: "UINT", namespace: null },
  ADDRESS: { id: "ADDRESS", namespace: null },
} as const;
