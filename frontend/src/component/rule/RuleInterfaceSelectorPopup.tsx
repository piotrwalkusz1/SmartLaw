import Id from "../../model/Id";
import React from "react";
import { searchRulesInterfaces } from "../../service/RuleService";
import SelectorPopup from "../../common/SelectorPopup";
import RuleInterface from "../../model/RuleInterface";

interface RuleInterfaceSelectorPopupProps {
  projectId: string;
  show: boolean;
  handleClose: () => void;
  onRuleInterfaceSelected: (id: Id) => void;
}

const RuleInterfaceSelectorPopup = ({ projectId, show, handleClose, onRuleInterfaceSelected }: RuleInterfaceSelectorPopupProps) => {
  return (
    <SelectorPopup
      header="Select rule interface"
      show={show}
      handleClose={handleClose}
      search={(searchPhrase) => searchRulesInterfaces({ searchPhrase, projectId })}
      onSelect={(ruleInterface: RuleInterface) => {
        onRuleInterfaceSelected(ruleInterface.id);
        handleClose();
      }}
      display={(ruleInterface: RuleInterface) => <div>{ruleInterface.name}</div>}
    />
  );
};

export default RuleInterfaceSelectorPopup;
