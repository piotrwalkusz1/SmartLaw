import { WrapWithTemplate } from "./WrapWithTemplate";
import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export enum ValidatorType {
  Regex = "Regex",
  Generic = "Generic",
  NumberRange = "NumberRange",
}

export default interface Validator {
  type: ValidatorType;
}

export interface ValidatorTemplate extends WrapWithTemplate<Validator> {}

export const validatorMeta: BaseMetaData<Validator, ValidatorType> = buildBaseMetaData<Validator, ValidatorType>(
  "type",
  ValidatorType,
  null,
  {
    type: excludeFromTemplate(enumMeta(ValidatorType)),
  }
);
