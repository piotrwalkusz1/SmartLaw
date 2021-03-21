import DefinitionRef, { DefinitionRefUtils } from "./DefinitionRef";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { nullableMeta, stringMeta } from "../utils/Reflection";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { typeMeta } from "./Type";
import { TemplateType } from "./TemplateType";

export default interface ActionArgument {
  name: string;
  description: string | null;
  type: DefinitionRef;
}

export interface ActionArgumentTemplate extends WrapWithTemplate<ActionArgument> {}

export const ActionArgumentUtils = buildModelUtilsWithTemplate<ActionArgument, ActionArgumentTemplate>(
  TemplateType.ActionArgumentTemplate,
  {
    name: stringMeta,
    description: nullableMeta(stringMeta),
    type: DefinitionRefUtils.metaData,
  }
);
