/** @jsxImportSource @emotion/react */
import React from "react";
import MetaValue from "../../model/MetaValue";
import { List, Map } from "immutable";
import MetaArgument from "../../model/MetaArgument";
import MetaPrimitiveValue from "../../model/MetaPrimitiveValue";
import { Accordion, Button, Card, Form } from "react-bootstrap";
import DOMPurify from "dompurify";
import { DocumentEditorRuleInvocationElement } from "../../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";
import { css } from "@emotion/react";
import { ValidationResult } from "../../model/ValidationResult";
import Datetime from "react-datetime";
import SelectorButtonField from "../../common/SelectorButtonField";
import MetaRuleValue from "../../model/MetaRuleValue";
import RuleSelectorPopup from "../rule/RuleSelectorPopup";
import { prepareEmptyRuleInvocationArgument } from "../../service/RuleService";

const Styles = {
  holder: css`
    width: 20px;
    min-width: 20px;
    border: 1px solid;
    background-color: gray;
  `,
};

interface RuleInvocationViewProps {
  projectId: string;
  element: DocumentEditorRuleInvocationElement;
  onArgumentsChange: (newArguments: Map<String, MetaValue>) => void;
  onRemove: () => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationView = ({ projectId, element, onArgumentsChange, onRemove, dragHandleProps }: RuleInvocationViewProps) => {
  const renderContent = () => (
    <div
      dangerouslySetInnerHTML={{
        __html: DOMPurify.sanitize(element.extendedPresentationElement.naturalLanguageDocumentObject.content, { ALLOWED_TAGS: ["b"] }),
      }}
    />
  );
  const renderArgumentValidationResults = (validationResults?: List<ValidationResult>) => {
    if (validationResults === undefined) {
      return <div />;
    }

    return validationResults
      .map((validationResult) => validationResult.error)
      .filter((error) => error !== null)
      .map((error, index) => (
        <div key={index} style={{ color: "red" }}>
          {error}
        </div>
      ));
  };

  const renderArgumentValue = (ruleArgument: MetaArgument, ruleInvocationArgument: MetaValue) => {
    switch (ruleArgument.type.id) {
      case "String":
        return (
          <Form.Control
            type="text"
            value={(ruleInvocationArgument as MetaPrimitiveValue).value}
            onChange={(event) =>
              onArgumentsChange(
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(ruleArgument.name, {
                  ...ruleInvocationArgument,
                  value: event.target.value,
                } as MetaPrimitiveValue)
              )
            }
          />
        );
      case "Integer":
        return (
          <Form.Control
            type="number"
            value={(ruleInvocationArgument as MetaPrimitiveValue).value}
            onChange={(event) =>
              onArgumentsChange(
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(ruleArgument.name, {
                  ...ruleInvocationArgument,
                  value: event.target.value,
                } as MetaPrimitiveValue)
              )
            }
          />
        );
      case "LocalDate":
        return (
          <Datetime
            dateFormat={"YYYY-MM-DD"}
            timeFormat={false}
            utc={true}
            value={(ruleInvocationArgument as MetaPrimitiveValue).value}
            onChange={(value) =>
              onArgumentsChange(
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(ruleArgument.name, {
                  ...ruleInvocationArgument,
                  value: typeof value === "string" ? value : value.format("YYYY-MM-DD"),
                } as MetaPrimitiveValue)
              )
            }
          />
        );
      default:
        return (
          <SelectorButtonField
            value={(ruleInvocationArgument as MetaRuleValue).ruleId}
            onChange={(ruleId) =>
              onArgumentsChange(
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(ruleArgument.name, {
                  ...ruleInvocationArgument,
                  ruleId: ruleId,
                } as MetaRuleValue)
              )
            }
            popup={(show, handleClose, onSelect) => (
              <RuleSelectorPopup
                projectId={projectId}
                ruleInterfaceId={(ruleInvocationArgument as MetaRuleValue).ruleId}
                show={show}
                handleClose={handleClose}
                onRuleSelected={onSelect}
              />
            )}
          />
        );
    }
  };

  const renderArgumentEditor = (index: number, ruleArgument: MetaArgument, ruleInvocationArgument?: MetaValue) => {
    return (
      <div>
        <span>{ruleArgument.displayName || ruleArgument.name}</span>
        {renderArgumentValue(ruleArgument, ruleInvocationArgument || prepareEmptyRuleInvocationArgument(ruleArgument))}
      </div>
    );
  };

  const renderArgument = (
    index: number,
    ruleArgument: MetaArgument,
    ruleInvocationArgument: MetaValue | undefined,
    validationResults: List<ValidationResult> | undefined
  ) => {
    return (
      <div key={index} style={{ marginBottom: "15px" }}>
        {renderArgumentEditor(index, ruleArgument, ruleInvocationArgument)}
        {renderArgumentValidationResults(validationResults)}
      </div>
    );
  };
  const renderArgumentsEditor = () =>
    element.extendedPresentationElement.rule === null ? (
      <div />
    ) : (
      element.extendedPresentationElement.rule.arguments.map((ruleArgument, index) => {
        return renderArgument(
          index,
          ruleArgument,
          element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.get(ruleArgument.name),
          element.extendedPresentationElement.validationResults.get(ruleArgument.name)
        );
      })
    );

  return (
    <Accordion>
      <Card>
        <Accordion.Toggle {...dragHandleProps} as={Card.Header} eventKey="0">
          {renderContent()}
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <div style={{ marginBottom: "15px" }}>
              <Button variant={"danger"} onClick={onRemove}>
                Remove
              </Button>
            </div>
            {renderArgumentsEditor()}
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};
export default RuleInvocationView;
