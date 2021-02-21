import DOMPurify from "dompurify";
import { List, Map } from "immutable";
import { ValidationResult } from "../../model/ValidationResult";
import { Accordion, Button, Card } from "react-bootstrap";
import React from "react";
import RuleInvocation from "../../model/RuleInvocation";
import Rule from "../../model/Rule";
import RuleInvocationArgumentListEditor from "./RuleInvocationArgumentListEditor";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";

interface RuleInvocationPresentationViewProps {
  ruleInvocation: RuleInvocation;
  onRuleInvocationChange: (ruleInvocation: RuleInvocation) => void;
  rule: Rule;
  ruleContent: string;
  validationResults: Map<String, List<ValidationResult>>;
  onRemove?: () => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationPresentationView = ({
  ruleInvocation,
  onRuleInvocationChange,
  rule,
  ruleContent,
  validationResults,
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

  return (
    <Accordion>
      <Card>
        <Accordion.Toggle {...dragHandleProps} as={Card.Header} eventKey="0">
          {renderContent()}
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            {renderRemoveButton()}
            <RuleInvocationArgumentListEditor
              ruleArguments={rule.arguments}
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
  );
};

export default RuleInvocationPresentationView;
