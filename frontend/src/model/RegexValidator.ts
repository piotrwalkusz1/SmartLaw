import Validator, { ValidatorType } from "./Validator";
import { decodeString } from "../utils/Decoders";

export const prepareEmptyRegexValidator = (): RegexValidator => {
  return {
    type: ValidatorType.Regex,
    regex: "",
  };
};

export const decodeRegexValidator = (json: any): RegexValidator => {
  return {
    type: ValidatorType.Regex,
    regex: decodeString(json.regex),
  };
};

export default interface RegexValidator extends Validator {
  regex: string;
}
