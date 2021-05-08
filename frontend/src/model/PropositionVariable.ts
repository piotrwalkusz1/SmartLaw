import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";
import PropositionExpression, { propositionExpressionMeta, PropositionExpressionType } from "./PropositionExpression";

export default interface PropositionVariable extends PropositionExpression {}

export const propositionVariableMeta: BaseMetaData<PropositionVariable, PropositionExpressionType> = buildBaseMetaData<
  PropositionVariable,
  PropositionExpressionType
>("type", PropositionExpressionType, propositionExpressionMeta, {
  type: excludeFromTemplate(enumMeta(PropositionExpressionType)),
});
