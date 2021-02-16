import React from "react";
import RuleArgumentEditor from "./RuleArgumentEditor";
import MetaArgument from "../../model/MetaArgument";
import { List } from "immutable";
import { Accordion, Card } from "react-bootstrap";
import { RULE_ARGUMENT_TYPES } from "../../service/Types";
import ListEditor from "../../common/ListEditor";

interface RuleArgumentsEditorProps {
  ruleArguments: List<MetaArgument>;
  onRuleArgumentsChange: (ruleArguments: List<MetaArgument>) => void;
}

const RuleArgumentListEditor = ({ ruleArguments, onRuleArgumentsChange }: RuleArgumentsEditorProps) => {
  const prepareEmptyRuleArgument = (): MetaArgument => {
    return {
      name: "",
      displayName: null,
      description: null,
      type: RULE_ARGUMENT_TYPES[0],
    };
  };

  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          Arguments
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>
            <ListEditor
              items={ruleArguments}
              onItemsChange={onRuleArgumentsChange}
              header={(ruleArgument) => ruleArgument.name}
              content={(ruleArgument, onRuleArgumentChange) => (
                <RuleArgumentEditor ruleArgument={ruleArgument} onRuleArgumentChange={onRuleArgumentChange} />
              )}
              emptyItem={prepareEmptyRuleArgument}
            />
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleArgumentListEditor;
