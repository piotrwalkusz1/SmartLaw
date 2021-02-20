import { List } from "immutable";
import Id from "../../model/Id";
import ListEditor from "../../common/ListEditor";
import { useState } from "react";
import RuleInterfaceSelectorPopup from "./RuleInterfaceSelectorPopup";
import ExpandableArea from "../../common/ExpandableArea";

interface ImplementedRuleInterfacesSelectorProps {
  projectId: string;
  rulesInterfaces: List<Id>;
  onRulesInterfacesChange: (rulesInterfaces: List<Id>) => void;
}

const ImplementedRuleInterfacesSelector = ({
  projectId,
  rulesInterfaces,
  onRulesInterfacesChange,
}: ImplementedRuleInterfacesSelectorProps) => {
  const [showBrowser, setShowBrowser] = useState(false);

  return (
    <ExpandableArea header="Implemented interfaces">
      <div>
        <ListEditor
          items={rulesInterfaces}
          onItemsChange={onRulesInterfacesChange}
          header={(ruleInterfaceId) => ruleInterfaceId.id}
          content={(ruleInterfaceId) => <div>{ruleInterfaceId.id}</div>}
          onItemAdd={() => setShowBrowser(true)}
        />
        <RuleInterfaceSelectorPopup
          projectId={projectId}
          show={showBrowser}
          handleClose={() => setShowBrowser(false)}
          onRuleInterfaceSelected={(ruleInterfaceId) => onRulesInterfacesChange(rulesInterfaces.push(ruleInterfaceId))}
        />
      </div>
    </ExpandableArea>
  );
};

export default ImplementedRuleInterfacesSelector;
