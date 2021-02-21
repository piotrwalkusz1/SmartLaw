import Validator, { ValidatorType } from "./Validator";
import { decodeNumber } from "../utils/Decoders";

export const prepareEmptyNumberRangeValidator = (): NumberRangeValidator => {
  return {
    type: ValidatorType.NumberRange,
    minValue: 0,
    maxValue: 100,
  };
};

export const decodeNumberRangeValidator = (json: any): NumberRangeValidator => {
  return {
    type: ValidatorType.NumberRange,
    minValue: decodeNumber(json.minValue),
    maxValue: decodeNumber(json.maxValue),
  };
};

export default interface NumberRangeValidator extends Validator {
  minValue: number;
  maxValue: number;
}
