import Element, { elementMeta, ElementType } from "./Element";
import { List } from "immutable";
import { WrapWithTemplate } from "./WrapWithTemplate";
import GenericParameter, { GenericParameterUtils } from "./GenericParameter";
import FunctionArgumentDefinition, { FunctionArgumentDefinitionUtils } from "./FunctionArgumentDefinition";
import Statement, { statementMeta } from "./Statement";
import { listMeta, nullableMeta, stringMeta } from "../utils/Reflection";
import FunctionResult, { FunctionResultUtils } from "./FunctionResult";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";

export default interface FunctionElement extends Element {
  name: string;
  description: string | null;
  parameters: List<GenericParameter>;
  arguments: List<FunctionArgumentDefinition>;
  body: List<Statement>;
  result: FunctionResult | null;
}

export interface FunctionElementTemplate extends WrapWithTemplate<FunctionElement> {}

export const FunctionElementUtils = buildDerivativeModelUtilsWithTemplate<FunctionElement, Element, ElementType, FunctionElementTemplate>(
  elementMeta,
  ElementType.Function,
  TemplateType.FunctionTemplate,
  {
    name: stringMeta,
    description: nullableMeta(stringMeta),
    parameters: listMeta(GenericParameterUtils.metaData),
    arguments: listMeta(FunctionArgumentDefinitionUtils.metaData),
    body: listMeta(statementMeta),
    result: nullableMeta(FunctionResultUtils.metaData),
  }
);
