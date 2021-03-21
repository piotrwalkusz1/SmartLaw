import Type, { TypeKind, typeMeta } from "./Type";
import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface InterfaceRef extends Type {
  interfaceId: Id;
  parameters: List<Type>;
}

export interface InterfaceRefTemplate extends WrapWithTemplate<InterfaceRef> {}

export const InterfaceRefUtils = buildDerivativeModelUtilsWithTemplate<InterfaceRef, Type, TypeKind, InterfaceRefTemplate>(
  typeMeta,
  TypeKind.InterfaceRef,
  TemplateType.InterfaceRefTemplate,
  {
    interfaceId: IdUtils.metaData,
    parameters: listMeta(typeMeta),
  }
);
