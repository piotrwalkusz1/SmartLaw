import { buildModelUtils } from "../utils/ModelUtils";
import { stringMeta } from "../utils/Reflection";

export default interface ElementValidationError {
  message: string;
}

export const ElementValidationErrorUtils = buildModelUtils<ElementValidationError>({
  message: stringMeta,
});
