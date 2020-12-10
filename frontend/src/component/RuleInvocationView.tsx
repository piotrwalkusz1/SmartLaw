/** @jsxImportSource @emotion/react */
import React from "react";
import MetaValue, { MetaValueType } from "../model/MetaValue";
import { List } from "immutable";
import MetaArgument from "../model/MetaArgument";
import MetaPrimitiveValue from "../model/MetaPrimitiveValue";
import { Accordion, Card } from "react-bootstrap";
import DOMPurify from "dompurify";
import { DocumentEditorRuleInvocationElement } from "../page/ContractPage";
import { DraggableProvidedDragHandleProps } from "react-beautiful-dnd";
import { css } from "@emotion/react";
import { ValidationResult } from "../model/ValidationResult";

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
  dragHandleProps?: DraggableProvidedDragHandleProps;
}

const RuleInvocationView = ({ element, onArgumentsChange, dragHandleProps }: RuleInvocationViewProps) => {
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
      .map((error, index) => <div key={index}>{error}</div>);
  };
  const renderArgumentEditor = (index: number, ruleArgument: MetaArgument, ruleInvocationArgument?: MetaValue) => {
    if (ruleInvocationArgument === undefined) {
      return <div>Null value</div>;
    }

    switch (ruleArgument.type.id) {
      case "String":
        if (ruleInvocationArgument.type !== MetaValueType.Primitive) {
          return (
            <div>
              Bad type of type. Expected {MetaValueType.Primitive} but was {ruleInvocationArgument.type}
            </div>
          );
        }
        return (
          <div>
            <span>{ruleArgument.displayName || ruleArgument.name}</span>
            <input
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
          </div>
        );
      default:
        return <div key={index}>Unknown argument type {ruleArgument.type.id}</div>;
    }
  };
  const renderArgument = (
    index: number,
    ruleArgument: MetaArgument,
    ruleInvocationArgument: MetaValue | undefined,
    validationResults: List<ValidationResult> | undefined
  ) => {
    return (
      <div key={index}>
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
    <div style={{ display: "flex" }}>
      <div css={Styles.holder} {...dragHandleProps} />
      <div style={{ flexGrow: 1 }}>
        <Accordion>
          <Card>
            <Accordion.Toggle as={Card.Header} eventKey="0">
              {renderContent()}
            </Accordion.Toggle>
            <Accordion.Collapse eventKey="0">
              <Card.Body>{renderArgumentsEditor()}</Card.Body>
            </Accordion.Collapse>
          </Card>
        </Accordion>
      </div>
    </div>
  );
};

export default RuleInvocationView;
