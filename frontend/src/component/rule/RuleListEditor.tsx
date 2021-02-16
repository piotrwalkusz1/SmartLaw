import RuleEditor from "./RuleEditor";
import React from "react";
import Rule from "../../model/Rule";
import { List, Map } from "immutable";
import { prepareStaticTemplate } from "../../model/StaticTemplate";
import ListEditor from "../../common/ListEditor";

interface RuleListEditorProps {
  rules: List<Rule>;
  onRulesChange: (rules: List<Rule>) => void;
}

const RuleListEditor = ({ rules, onRulesChange }: RuleListEditorProps) => {
  const prepareEmptyRule = (): Rule => {
    return {
      id: { id: "", namespace: null },
      name: "",
      description: null,
      arguments: List(),
      content: prepareStaticTemplate(""),
      elements: prepareStaticTemplate(List()),
      outputs: Map(),
    };
  };

  return (
    <ListEditor
      items={rules}
      onItemsChange={onRulesChange}
      header={(rule) => rule.name}
      content={(rule, onRuleChange) => <RuleEditor rule={rule} onRuleChange={onRuleChange} />}
      emptyItem={prepareEmptyRule}
    />
  );
};

export default RuleListEditor;
