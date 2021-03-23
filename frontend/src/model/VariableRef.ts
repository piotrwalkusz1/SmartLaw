import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";
import FunctionArgument, { functionArgumentMeta, FunctionArgumentType } from "./FunctionArgument";

export default interface VariableRef extends FunctionArgument {}

export const variableRefMeta: BaseMetaData<VariableRef, FunctionArgumentType> = buildBaseMetaData<VariableRef, FunctionArgumentType>(
  "type",
  FunctionArgumentType,
  functionArgumentMeta,
  {
    type: excludeFromTemplate(enumMeta(FunctionArgumentType)),
  }
);
