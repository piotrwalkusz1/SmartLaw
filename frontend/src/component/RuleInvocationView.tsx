/** @jsxImportSource @emotion/react */
import React from "react";
import MetaValue, { MetaValueType } from "../model/MetaValue";
import { List } from "immutable";
import MetaArgument from "../model/MetaArgument";
import MetaPrimitiveValue from "../model/MetaPrimitiveValue";
import { Accordion, Button, Card, Form } from "react-bootstrap";
import DOMPurify from "dompurify";
import { DocumentEditorRuleInvocationElement } from "../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";
import { css } from "@emotion/react";
import { ValidationResult } from "../model/ValidationResult";
import Datetime from "react-datetime";

const Styles = {
  holder: css`
    width: 20px;
    min-width: 20px;
    border: 1px solid;
    background-color: gray;
  `,
};

interface RuleInvocationViewProps {
  element: DocumentEditorRuleInvocationElement;
  onArgumentsChange: (newArguments: List<MetaValue>) => void;
  onRemove: () => void;
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationView = ({ element, onArgumentsChange, onRemove, dragHandleProps }: RuleInvocationViewProps) => {
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

  const renderArgumentValue = (index: number, ruleArgument: MetaArgument, ruleInvocationArgument: MetaPrimitiveValue) => {
    switch (ruleArgument.type.id) {
      case "String":
        return (
          <Form.Control
            type="text"
            value={(ruleInvocationArgument as MetaPrimitiveValue).value}
            onChange={(event) =>
              onArgumentsChange(
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(index, {
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
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(index, {
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
                element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.set(index, {
                  ...ruleInvocationArgument,
                  value: typeof value === "string" ? value : value.format("YYYY-MM-DD"),
                } as MetaPrimitiveValue)
              )
            }
          />
        );
      default:
        return <div>Unknown argument type {ruleArgument.type.id}</div>;
    }
  };

  const renderArgumentEditor = (index: number, ruleArgument: MetaArgument, ruleInvocationArgument?: MetaValue) => {
    if (ruleInvocationArgument === undefined) {
      return <div>Null value</div>;
    }

    if (ruleInvocationArgument.type !== MetaValueType.Primitive) {
      return (
        <div>
          Bad type of type. Expected {MetaValueType.Primitive} but was {ruleInvocationArgument.type}
        </div>
      );
    }
    const metaPrimitiveValue = ruleInvocationArgument as MetaPrimitiveValue;

    return (
      <div>
        <span>{ruleArgument.displayName || ruleArgument.name}</span>
        {renderArgumentValue(index, ruleArgument, metaPrimitiveValue)}
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
          element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.get(index),
          element.extendedPresentationElement.validationResults.get(index)
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
