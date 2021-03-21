import Validator, { validatorMeta, ValidatorType } from "./Validator";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { numberMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface NumberRangeValidator extends Validator {
  minValue: number;
  maxValue: number;
}

export interface NumberRangeValidatorTemplate extends WrapWithTemplate<NumberRangeValidator> {}

export const NumberRangeValidatorUtils = buildDerivativeModelUtilsWithTemplate<
  NumberRangeValidator,
  Validator,
  ValidatorType,
  NumberRangeValidatorTemplate
>(validatorMeta, ValidatorType.NumberRange, TemplateType.NumberRangeValidator, {
  minValue: numberMeta,
  maxValue: numberMeta,
});
