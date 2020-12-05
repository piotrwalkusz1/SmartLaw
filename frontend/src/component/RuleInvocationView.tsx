import React, { useContext, useEffect, useState } from "react";
import RuleInvocation from "../model/RuleInvocation";
import MetaValue, { MetaValueType } from "../model/MetaValue";
import { List } from "immutable";
import Rule from "../model/Rule";
import { getRule } from "../service/RuleService";
import { DocumentEditorContext, DocumentEditorContextProps } from "../context/DocumentEditorContext";
import MetaArgument from "../model/MetaArgument";
import NaturalLanguageProvision from "../model/NaturalLanguageProvision";
import MetaPrimitiveValue from "../model/MetaPrimitiveValue";
import { Accordion, Card } from "react-bootstrap";
import DOMPurify from "dompurify";

interface RuleInvocationViewProps {
  ruleInvocation: RuleInvocation;
  onArgumentsChange: (newArguments: List<MetaValue>) => void;
  naturalLanguageProvision: NaturalLanguageProvision;
}

const RuleInvocationView = ({ ruleInvocation, onArgumentsChange, naturalLanguageProvision }: RuleInvocationViewProps) => {
  const [rule, setRule] = useState<Rule | null>(null);
  const { projectId, documentId } = useContext(DocumentEditorContext) as DocumentEditorContextProps;
  useEffect(() => {
    getRule(ruleInvocation.ruleId, projectId).then((rule) => setRule((_) => rule));
  }, [ruleInvocation.ruleId, projectId]);

  const renderContent = () => (
    <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(naturalLanguageProvision.content, { ALLOWED_TAGS: ["b"] }) }} />
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
                  ruleInvocation.arguments.set(index, {
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
    rule === null ? (
      <div />
    ) : (
      rule.arguments.map((ruleArgument, index) => {
        return renderArgument(index, ruleArgument, ruleInvocation.arguments.get(index));
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
