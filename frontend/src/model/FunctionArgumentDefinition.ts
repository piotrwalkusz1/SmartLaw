import { WrapWithTemplate } from "./WrapWithTemplate";
import Type, { typeMeta } from "./Type";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface FunctionArgumentDefinition {
  name: string;
  description: string | null;
  type: Type;
}

export interface FunctionArgumentDefinitionTemplate extends WrapWithTemplate<FunctionArgumentDefinition> {}

export const FunctionArgumentDefinitionUtils = buildModelUtilsWithTemplate<FunctionArgumentDefinition, FunctionArgumentDefinitionTemplate>(
  TemplateType.FunctionArgumentDefinitionTemplate,
  {
    name: stringMeta,
    description: nullableMeta(stringMeta),
    type: typeMeta,
  }
);
