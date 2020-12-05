import React from "react";
import MetaValue, { MetaValueType } from "../model/MetaValue";
import { List } from "immutable";
import MetaArgument from "../model/MetaArgument";
import MetaPrimitiveValue from "../model/MetaPrimitiveValue";
import { Accordion, Card } from "react-bootstrap";
import DOMPurify from "dompurify";
import { DocumentEditorRuleInvocationElement } from "../page/ContractPage";

interface RuleInvocationViewProps {
  element: DocumentEditorRuleInvocationElement;
  onArgumentsChange: (newArguments: List<MetaValue>) => void;
}

const RuleInvocationView = ({ element, onArgumentsChange }: RuleInvocationViewProps) => {
  const renderContent = () => (
    <div
      dangerouslySetInnerHTML={{
        __html: DOMPurify.sanitize(element.extendedPresentationElement.naturalLanguageDocumentObject.content, { ALLOWED_TAGS: ["b"] }),
      }}
    />
  );
  const renderArgument = (index: number, ruleArgument: MetaArgument, ruleInvocationArgument?: MetaValue) => {
    if (ruleInvocationArgument === undefined) {
      return <div key={index}>Null value</div>;
    }

    switch (ruleArgument.type.id) {
      case "String":
        if (ruleInvocationArgument.type !== MetaValueType.Primitive) {
          return (
            <div key={index}>
              Bad type of type. Expected {MetaValueType.Primitive} but was {ruleInvocationArgument.type}
            </div>
          );
        }
        return (
          <div key={index}>
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
  const renderArgumentsEditor = () =>
    element.extendedPresentationElement.rule === null ? (
      <div />
    ) : (
      element.extendedPresentationElement.rule.arguments.map((ruleArgument, index) => {
        return renderArgument(
          index,
          ruleArgument,
          element.extendedPresentationElement.presentationElement.ruleInvocation.arguments.get(index)
        );
      })
    );

  return (
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
  );
};

export default RuleInvocationView;
