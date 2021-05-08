import { WrapWithTemplate } from "./WrapWithTemplate";
import { List } from "immutable";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import PropositionVariable, { propositionVariableMeta } from "./PropositionVariable";
import DefinitionRef, { DefinitionRefUtils } from "./DefinitionRef";
import { PropositionExpressionType } from "./PropositionExpression";
import PropositionTerm, { propositionTermMeta } from "./PropositionTerm";

export default interface PropositionComplexVariable extends PropositionTerm {
  definition: DefinitionRef;
  variables: List<PropositionVariable>;
}

export interface PropositionComplexVariableTemplate extends WrapWithTemplate<PropositionComplexVariable> {}

export const PropositionComplexVariableUtils = buildDerivativeModelUtilsWithTemplate<
  PropositionComplexVariable,
  PropositionTerm,
  PropositionExpressionType,
  PropositionComplexVariableTemplate
>(propositionTermMeta, PropositionExpressionType.Complex, TemplateType.PropositionComplexVariableTemplate, {
  definition: DefinitionRefUtils.metaData,
  variables: listMeta(propositionVariableMeta),
});
