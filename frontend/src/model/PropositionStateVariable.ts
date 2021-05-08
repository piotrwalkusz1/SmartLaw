import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import { PropositionExpressionType } from "./PropositionExpression";
import PropositionTerm, { propositionTermMeta } from "./PropositionTerm";
import Id, { IdUtils } from "./Id";

export default interface PropositionStateVariable extends PropositionTerm {
  state: Id;
}

export interface PropositionStateVariableTemplate extends WrapWithTemplate<PropositionStateVariable> {}

export const PropositionStateVariableUtils = buildDerivativeModelUtilsWithTemplate<
  PropositionStateVariable,
  PropositionTerm,
  PropositionExpressionType,
  PropositionStateVariableTemplate
>(propositionTermMeta, PropositionExpressionType.State, TemplateType.PropositionStateVariableTemplate, {
  state: IdUtils.metaData,
});
