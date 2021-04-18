import Validator, { validatorMeta, ValidatorType } from "./Validator";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta, withDefaultValue } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface GenericValidator extends Validator {
  expressionEvaluatorType: string;
  expression: string;
}

export interface GenericValidatorTemplate extends WrapWithTemplate<GenericValidator> {}

export const GenericValidatorUtils = buildDerivativeModelUtilsWithTemplate<
  GenericValidator,
  Validator,
  ValidatorType,
  GenericValidatorTemplate
>(validatorMeta, ValidatorType.Generic, TemplateType.GenericValidatorTemplate, {
  expressionEvaluatorType: withDefaultValue(stringMeta, "FreeMarker"),
  expression: stringMeta,
});
