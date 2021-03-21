import { WrapWithTemplate } from "./WrapWithTemplate";
import Id, { IdUtils } from "./Id";
import VariableRef, { variableRefMeta } from "./VariableRef";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { FunctionArgumentType } from "./FunctionArgument";
import { TemplateType } from "./TemplateType";

export default interface StateVariableRef extends VariableRef {
  state: Id;
}

export interface StateVariableRefTemplate extends WrapWithTemplate<StateVariableRef> {}

export const StateVariableRefUtils = buildDerivativeModelUtilsWithTemplate<
  StateVariableRef,
  VariableRef,
  FunctionArgumentType,
  StateVariableRefTemplate
>(variableRefMeta, FunctionArgumentType.StateVariableRef, TemplateType.StateVariableRefTemplate, {
  state: IdUtils.metaData,
});
