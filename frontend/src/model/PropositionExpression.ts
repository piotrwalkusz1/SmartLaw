import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export enum PropositionExpressionType {
  Complex = "Complex",
  State = "State",
  Primitive = "Primitive",
}

export default interface PropositionExpression {
  type: PropositionExpressionType;
}

export const propositionExpressionMeta: BaseMetaData<PropositionExpression, PropositionExpressionType> = buildBaseMetaData<
  PropositionExpression,
  PropositionExpressionType
>("type", PropositionExpressionType, null, {
  type: excludeFromTemplate(enumMeta(PropositionExpressionType)),
});
