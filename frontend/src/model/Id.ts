import { nullableMeta, stringMeta } from "../utils/Reflection";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";

export default interface Id {
  id: string;
  namespace: string | null;
}

export interface IdTemplate extends WrapWithTemplate<Id> {}

export const IdUtils = buildModelUtilsWithTemplate<Id, IdTemplate>(TemplateType.IdTemplate, {
  id: stringMeta,
  namespace: nullableMeta(stringMeta),
});

export const areIdsEqual = (firstId: Id, secondId: Id): boolean => {
  return firstId.id === secondId.id && firstId.namespace === secondId.namespace;
};
