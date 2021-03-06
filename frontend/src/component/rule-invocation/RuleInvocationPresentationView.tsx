import DOMPurify from "dompurify";
import { List, Map } from "immutable";
import { ValidationResult } from "../../model/ValidationResult";
import { Accordion, Button, Card } from "react-bootstrap";
import React from "react";
import RuleInvocation from "../../model/RuleInvocation";
import Rule from "../../model/Rule";
import RuleInvocationArgumentListEditor from "./RuleInvocationArgumentListEditor";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";
import ElementValidationError from "../../model/ElementValidationError";

interface RuleInvocationPresentationViewProps {
  ruleInvocation: RuleInvocation;
  onRuleInvocationChange: (ruleInvocation: RuleInvocation) => void;
  rule: Rule | null;
  ruleContent: string;
  validationResults: Map<string, List<ValidationResult>>;
  elementValidationErrors?: List<ElementValidationError>;
  onRemove?: () => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationPresentationView = ({
  ruleInvocation,
  onRuleInvocationChange,
  rule,
  ruleContent,
  validationResults,
  elementValidationErrors,
  onRemove,
  dragHandleProps,
}: RuleInvocationPresentationViewProps) => {
  const renderContent = () => (
    <div
      dangerouslySetInnerHTML={{
        __html: DOMPurify.sanitize(ruleContent, { ALLOWED_TAGS: ["b"] }),
      }}
    />
  );

  const renderRemoveButton = () => {
    if (onRemove) {
      return (
        <div style={{ marginBottom: "15px" }}>
          <Button variant={"danger"} onClick={onRemove}>
            Remove
          </Button>
        </div>
      );
    } else {
      return <> </>;
    }
  };

  const renderElementValidationErrors = () => {
    if (elementValidationErrors) {
      return (
        <div style={{ color: "red" }}>
          {elementValidationErrors.map((error, index) => {
            return <div key={index}>{error.message}</div>;
          })}
        </div>
      );
    } else {
      return <></>;
    }
  };

  const isAnyValidationError = (): boolean => {
    if (elementValidationErrors && !elementValidationErrors.isEmpty()) {
      return true;
    }

    return !!List(validationResults.values())
      .flatMap((validationResults) => validationResults)
      .find((validationResult) => validationResult.error !== null);
  };

  return (
    <div style={isAnyValidationError() ? { border: "solid 2px red" } : {}}>
      <Accordion>
        <Card>
          <Accordion.Toggle {...dragHandleProps} as={Card.Header} eventKey="0">
            {renderContent()}
          </Accordion.Toggle>
          <Accordion.Collapse eventKey="0">
            <Card.Body>
              {renderRemoveButton()}
              {renderElementValidationErrors()}
              <RuleInvocationArgumentListEditor
                ruleArguments={rule ? rule.arguments : List()}
                ruleInvocationArguments={ruleInvocation.arguments}
                onRuleInvocationArgumentsChange={(newArguments) =>
                  onRuleInvocationChange({
                    ...ruleInvocation,
                    arguments: newArguments,
                  })
                }
                validationResults={validationResults}
              />
            </Card.Body>
          </Accordion.Collapse>
        </Card>
      </Accordion>
    </div>
  );
};

export default RuleInvocationPresentationView;
