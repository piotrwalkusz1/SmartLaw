import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import PropositionVariable, { propositionVariableMeta } from "./PropositionVariable";
import { PropositionExpressionType } from "./PropositionExpression";
import Id, { IdUtils } from "./Id";

export default interface PropositionPrimitiveVariable extends PropositionVariable {
  valueType: Id;
  value: string;
}

export interface PropositionPrimitiveVariableTemplate extends WrapWithTemplate<PropositionPrimitiveVariable> {}

export const PropositionPrimitiveVariableUtils = buildDerivativeModelUtilsWithTemplate<
  PropositionPrimitiveVariable,
  PropositionVariable,
  PropositionExpressionType,
  PropositionPrimitiveVariableTemplate
>(propositionVariableMeta, PropositionExpressionType.Primitive, TemplateType.PropositionPrimitiveVariableTemplate, {
  valueType: IdUtils.metaData,
  value: stringMeta,
});
