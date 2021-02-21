import Validator, { ValidatorType } from "./Validator";
import { decodeString } from "../utils/Decoders";

export const prepareEmptyGenericValidator = (): GenericValidator => {
  return {
    type: ValidatorType.Generic,
    expressionEvaluatorType: "FreeMarker",
    expression: "",
  };
};

export const decodeGenericValidator = (json: any): GenericValidator => {
  return {
    type: ValidatorType.Generic,
    expressionEvaluatorType: decodeString(json.expressionEvaluatorType),
    expression: decodeString(json.expression),
  };
};

export default interface GenericValidator extends Validator {
  expressionEvaluatorType: string;
  expression: string;
}
