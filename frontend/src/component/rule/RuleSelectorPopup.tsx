import Id from "../../model/Id";
import React from "react";
import { searchRules } from "../../service/RuleService";
import SelectorPopup from "../../common/SelectorPopup";
import Rule from "../../model/Rule";

interface RuleSelectorPopupProps {
  projectId: string;
  ruleInterfaceId: Id;
  show: boolean;
  handleClose: () => void;
  onRuleSelected: (id: Id) => void;
}

const RuleSelectorPopup = ({ projectId, ruleInterfaceId, show, handleClose, onRuleSelected }: RuleSelectorPopupProps) => {
  return (
    <SelectorPopup
      header="Select rule"
      show={show}
      handleClose={handleClose}
      search={(searchPhrase) => searchRules({ searchPhrase, ruleInterfaceId, projectId })}
      onSelect={(rule: Rule) => onRuleSelected(rule.id)}
      display={(rule: Rule) => <div>{rule.name}</div>}
    />
  );
};

export default RuleSelectorPopup;
