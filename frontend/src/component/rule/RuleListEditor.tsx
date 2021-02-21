import RuleEditor from "./RuleEditor";
import React from "react";
import Rule from "../../model/Rule";
import { List, Map } from "immutable";
import { prepareStaticTemplate } from "../../model/StaticTemplate";
import ListEditor from "../../common/ListEditor";
import Id from "../../model/Id";

interface RuleListEditorProps {
  projectId: string;
  rules: List<Rule>;
  onRulesChange: (rules: List<Rule>) => void;
  ruleArgumentTypes: List<Id>;
}

const RuleListEditor = ({ projectId, rules, onRulesChange, ruleArgumentTypes }: RuleListEditorProps) => {
  const prepareEmptyRule = (): Rule => {
    return {
      id: { id: "", namespace: null },
      name: "",
      description: null,
      arguments: List(),
      content: prepareStaticTemplate(""),
      elements: prepareStaticTemplate(List()),
      outputs: Map(),
      interfaces: List(),
    };
  };

  return (
    <ListEditor
      items={rules}
      onItemsChange={onRulesChange}
      header={(rule) => rule.name}
      content={(rule, onRuleChange) => (
        <RuleEditor projectId={projectId} rule={rule} onRuleChange={onRuleChange} ruleArgumentTypes={ruleArgumentTypes} />
      )}
      emptyItem={prepareEmptyRule}
      allowRemove={true}
    />
  );
};

export default RuleListEditor;
