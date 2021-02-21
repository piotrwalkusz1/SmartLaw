import React from "react";
import RuleArgumentEditor from "./RuleArgumentEditor";
import MetaArgument from "../../model/MetaArgument";
import { List } from "immutable";
import { Accordion, Card } from "react-bootstrap";
import ListEditor from "../../common/ListEditor";
import Id, { prepareEmptyId } from "../../model/Id";

interface RuleArgumentsEditorProps {
  ruleArguments: List<MetaArgument>;
  onRuleArgumentsChange: (ruleArguments: List<MetaArgument>) => void;
  ruleArgumentTypes: List<Id>;
}

const RuleArgumentListEditor = ({ ruleArguments, onRuleArgumentsChange, ruleArgumentTypes }: RuleArgumentsEditorProps) => {
  const prepareEmptyRuleArgument = (): MetaArgument => {
    return {
      name: "",
      displayName: null,
      description: null,
      type: ruleArgumentTypes.get(0) || prepareEmptyId(),
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
                <RuleArgumentEditor
                  ruleArgument={ruleArgument}
                  onRuleArgumentChange={onRuleArgumentChange}
                  ruleArgumentTypes={ruleArgumentTypes}
                />
              )}
              emptyItem={prepareEmptyRuleArgument}
              allowRemove={true}
            />
          </Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default RuleArgumentListEditor;
