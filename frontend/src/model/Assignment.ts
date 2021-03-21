import { WrapWithTemplate } from "./WrapWithTemplate";
import Expression, { expressionMeta } from "./Expression";
import VariableRef, { variableRefMeta } from "./VariableRef";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import Statement, { statementMeta, StatementType } from "./Statement";
import { TemplateType } from "./TemplateType";

export default interface Assignment extends Statement {
  variable: VariableRef;
  value: Expression;
}

export interface AssignmentTemplate extends WrapWithTemplate<Assignment> {}

export const AssignmentUtils = buildDerivativeModelUtilsWithTemplate<Assignment, Statement, StatementType, AssignmentTemplate>(
  statementMeta,
  StatementType.Assignment,
  TemplateType.AssignmentTemplate,
  {
    variable: variableRefMeta,
    value: expressionMeta,
  }
);
