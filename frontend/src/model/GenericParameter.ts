import { WrapWithTemplate } from "./WrapWithTemplate";
import Type, { typeMeta } from "./Type";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface GenericParameter {
  name: string;
  baseType: Type | null;
}

export interface GenericParameterTemplate extends WrapWithTemplate<GenericParameter> {}

export const GenericParameterUtils = buildModelUtilsWithTemplate<GenericParameter, GenericParameterTemplate>(
  TemplateType.GenericParameterTemplate,
  {
    name: stringMeta,
    baseType: nullableMeta(typeMeta),
  }
);
