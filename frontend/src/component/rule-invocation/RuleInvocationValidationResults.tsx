import { ValidationResult } from "../../model/ValidationResult";
import { List } from "immutable";
import React from "react";

interface RuleInvocationValidationResultsProps {
  validationResults: List<ValidationResult>;
}

const RuleInvocationValidationResults = ({ validationResults }: RuleInvocationValidationResultsProps) => {
  return (
    <div>
      {validationResults
        .map((validationResult) => validationResult.error)
        .filter((error) => error !== null)
        .map((error, index) => (
          <div key={index} style={{ color: "red" }}>
            {error}
          </div>
        ))}
    </div>
  );
};

export default RuleInvocationValidationResults;
