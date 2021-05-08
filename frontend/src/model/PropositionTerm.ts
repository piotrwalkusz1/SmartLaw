import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";
import PropositionExpression, { propositionExpressionMeta, PropositionExpressionType } from "./PropositionExpression";
import PropositionVariable, { propositionVariableMeta } from "./PropositionVariable";

export default interface PropositionTerm extends PropositionVariable {}

export const propositionTermMeta: BaseMetaData<PropositionTerm, PropositionExpressionType> = buildBaseMetaData<
  PropositionTerm,
  PropositionExpressionType
>("type", PropositionExpressionType, propositionVariableMeta, {
  type: excludeFromTemplate(enumMeta(PropositionExpressionType)),
});
