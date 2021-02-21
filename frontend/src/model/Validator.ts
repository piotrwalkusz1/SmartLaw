import { decodeRegexValidator } from "./RegexValidator";
import { decodeGenericValidator } from "./GenericValidator";
import { decodeNumberRangeValidator } from "./NumberRangeValidator";

export enum ValidatorType {
  Regex = "Regex",
  Generic = "Generic",
  NumberRange = "NumberRange",
}

export const decodeValidator = (json: any): Validator => {
  switch (json.type) {
    case ValidatorType.Regex:
      return decodeRegexValidator(json);
    case ValidatorType.Generic:
      return decodeGenericValidator(json);
    case ValidatorType.NumberRange:
      return decodeNumberRangeValidator(json);
    default:
      throw Error("Decoding validator type " + json.type + " is not supported");
  }
};

export default interface Validator {
  type: ValidatorType;
}
