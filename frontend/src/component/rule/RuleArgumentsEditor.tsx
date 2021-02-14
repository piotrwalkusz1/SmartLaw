import React from "react";
import RuleArgumentEditor from "./RuleArgumentEditor";
import MetaArgument from "../../model/MetaArgument";
import { List } from "immutable";
import { Accordion, Card } from "react-bootstrap";

interface RuleArgumentsEditorProps {
  ruleArguments: List<MetaArgument>;
  onRuleArgumentsChange: (ruleArguments: List<MetaArgument>) => void;
}

const RuleArgumentsEditor = ({ ruleArguments, onRuleArgumentsChange }: RuleArgumentsEditorProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Arguments
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <div style={{ paddingLeft: "15px" }}>
              {ruleArguments.map((ruleArgument, index) => (
                <RuleArgumentEditor
                  key={index}
                  ruleArgument={ruleArgument}
                  onRuleArgumentChange={(ruleArgument) => onRuleArgumentsChange(ruleArguments.set(index, ruleArgument))}
                />
              ))}
            </div>
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleArgumentsEditor;
