import Validator, { validatorMeta, ValidatorType } from "./Validator";
import { buildDerivativeModelUtilsWithTemplate } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { TemplateType } from "./TemplateType";

export default interface RegexValidator extends Validator {
  regex: string;
}

export interface RegexValidatorTemplate extends WrapWithTemplate<RegexValidator> {}

export const RegexValidatorUtils = buildDerivativeModelUtilsWithTemplate<RegexValidator, Validator, ValidatorType, RegexValidatorTemplate>(
  validatorMeta,
  ValidatorType.Regex,
  TemplateType.RegexValidatorTemplate,
  {
    regex: stringMeta,
  }
);
