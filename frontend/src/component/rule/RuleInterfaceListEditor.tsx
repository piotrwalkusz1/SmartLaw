import ListEditor from "../../common/ListEditor";
import React from "react";
import RuleInterface from "../../model/RuleInterface";
import { List } from "immutable";
import RuleInterfaceEditor from "./RuleInterfaceEditor";
import { IdUtils } from "../../model/Id";

interface RuleInterfaceListEditorProps {
  rulesInterfaces: List<RuleInterface>;
  onRulesInterfacesChange: (rulesInterfaces: List<RuleInterface>) => void;
}

const RuleInterfaceListEditor = ({ rulesInterfaces, onRulesInterfacesChange }: RuleInterfaceListEditorProps) => {
  const prepareEmptyRuleInterface = (): RuleInterface => {
    return {
      id: IdUtils.create(),
      name: "",
      description: null,
    };
  };

  return (
    <ListEditor
      items={rulesInterfaces}
      onItemsChange={onRulesInterfacesChange}
      header={(ruleInterface) => ruleInterface.name}
      content={(ruleInterface, onRuleInterfaceChange) => (
        <RuleInterfaceEditor ruleInterface={ruleInterface} onRuleInterfaceChange={onRuleInterfaceChange} />
      )}
      emptyItem={prepareEmptyRuleInterface}
    />
  );
};

export default RuleInterfaceListEditor;
