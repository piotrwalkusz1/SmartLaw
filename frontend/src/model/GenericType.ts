import Type, { TypeKind, typeMeta } from "./Type";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { stringMeta } from "../utils/Reflection";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";

export default interface GenericType extends Type {
  name: string;
}

export interface GenericTypeTemplate extends WrapWithTemplate<GenericType> {}

export const GenericTypeUtils = buildDerivativeModelUtilsWithTemplate<GenericType, Type, TypeKind, GenericTypeTemplate>(
  typeMeta,
  TypeKind.GenericType,
  TemplateType.GenericTypeTemplate,
  {
    name: stringMeta,
  }
);
