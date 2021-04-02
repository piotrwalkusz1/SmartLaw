import { WrapWithTemplate } from "./WrapWithTemplate";
import Id, { IdUtils } from "./Id";
import VariableRef, { variableRefMeta } from "./VariableRef";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { TemplateType } from "./TemplateType";
import { StatementType } from "./Statement";

export default interface StateVariableRef extends VariableRef {
  state: Id;
}

export interface StateVariableRefTemplate extends WrapWithTemplate<StateVariableRef> {}

export const StateVariableRefUtils = buildDerivativeModelUtilsWithTemplate<
  StateVariableRef,
  VariableRef,
  StatementType,
  StateVariableRefTemplate
>(variableRefMeta, StatementType.StateVariableRef, TemplateType.StateVariableRefTemplate, {
  state: IdUtils.metaData,
});
