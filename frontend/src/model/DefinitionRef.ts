import Type, { TypeKind, typeMeta } from "./Type";
import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface DefinitionRef extends Type {
  definition: Id;
  parameters: List<Type>;
}

export interface DefinitionRefTemplate extends WrapWithTemplate<DefinitionRef> {}

export const DefinitionRefUtils = buildDerivativeModelUtilsWithTemplate<DefinitionRef, Type, TypeKind, DefinitionRefTemplate>(
  typeMeta,
  TypeKind.DefinitionRef,
  TemplateType.DefinitionRefTemplate,
  {
    definition: IdUtils.metaData,
    parameters: listMeta(typeMeta),
  }
);
