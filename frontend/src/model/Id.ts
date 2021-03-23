import { nullableMeta, stringMeta } from "../utils/Reflection";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import IdTemplateEditor from "../component/element/template/IdTemplateEditor";

export default interface Id {
  id: string;
  namespace: string | null;
}

export interface IdTemplate extends WrapWithTemplate<Id> {}

export const IdUtils = buildModelUtilsWithTemplate<Id, IdTemplate>(
  TemplateType.IdTemplate,
  {
    id: stringMeta,
    namespace: nullableMeta(stringMeta),
  },
  (template, onChange, fieldName) => {
    return IdTemplateEditor({ template, onChange, label: fieldName || "" });
  }
);

export const areIdsEqual = (firstId: Id, secondId: Id): boolean => {
  return firstId.id === secondId.id && firstId.namespace === secondId.namespace;
};
