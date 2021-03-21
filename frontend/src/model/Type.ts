import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";
import { WrapWithTemplate } from "./WrapWithTemplate";

export enum TypeKind {
  DefinitionRef = "DefinitionRef",
  GenericType = "GenericType",
  InterfaceRef = "InterfaceRef",
}

export default interface Type {
  type: TypeKind;
}

export interface TypeTemplate extends WrapWithTemplate<Type> {}

export const typeMeta: BaseMetaData<Type, TypeKind> = buildBaseMetaData<Type, TypeKind>("type", TypeKind, null, {
  type: excludeFromTemplate(enumMeta(TypeKind)),
});
