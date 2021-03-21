import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export enum FunctionArgumentType {
  StateVariableRef = "StateVariableRef",
}

export default interface FunctionArgument {
  type: string;
}

export const functionArgumentMeta: BaseMetaData<FunctionArgument, FunctionArgumentType> = buildBaseMetaData<
  FunctionArgument,
  FunctionArgumentType
>("type", FunctionArgumentType, null, {
  type: excludeFromTemplate(enumMeta(FunctionArgumentType)),
});
