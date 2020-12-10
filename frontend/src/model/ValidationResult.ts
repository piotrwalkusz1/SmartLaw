import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeValidationResult = (json: any): ValidationResult => {
  return new ValidationResult(decodeNullable(json.error, decodeString));
};

export class ValidationResult {
  error: string | null;

  constructor(error: string | null) {
    this.error = error;
  }
}
