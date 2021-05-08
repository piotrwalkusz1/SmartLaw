import Element, { elementMeta, ElementType } from "./Element";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";
import { List } from "immutable";
import PropositionExpression, { propositionExpressionMeta } from "./PropositionExpression";
import PropositionTerm, { propositionTermMeta } from "./PropositionTerm";

export default interface PropositionElement extends Element {
  head: List<PropositionTerm>;
  body: List<PropositionExpression>;
}

export interface PropositionElementTemplate extends WrapWithTemplate<PropositionElement> {}

export const PropositionElementUtils = buildDerivativeModelUtilsWithTemplate<
  PropositionElement,
  Element,
  ElementType,
  PropositionElementTemplate
>(elementMeta, ElementType.Proposition, TemplateType.PropositionTemplate, {
  head: listMeta(propositionTermMeta),
  body: listMeta(propositionExpressionMeta),
});
