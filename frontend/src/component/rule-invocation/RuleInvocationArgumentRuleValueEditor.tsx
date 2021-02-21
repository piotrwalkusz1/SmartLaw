import React from "react";
import MetaRuleValue from "../../model/MetaRuleValue";
import SelectorButtonField from "../../common/SelectorButtonField";
import RuleSelectorPopup from "../rule/RuleSelectorPopup";
import { ProjectContext } from "../../context/ProjectContext";
import Id from "../../model/Id";
import RuleInvocationEditor from "./RuleInvocationEditor";
import { Button } from "react-bootstrap";

interface RuleInvocationArgumentRuleValueEditorProps {
  argument: MetaRuleValue;
  onArgumentChange: (argument: MetaRuleValue) => void;
  ruleInterfaceId: Id;
}

const RuleInvocationArgumentRuleValueEditor = ({
  argument,
  onArgumentChange,
  ruleInterfaceId,
}: RuleInvocationArgumentRuleValueEditorProps) => {
  return (
    <ProjectContext.Consumer>
      {({ projectId }) => (
        <div>
          <div style={{ display: "flex", alignItems: "baseline" }}>
            <SelectorButtonField
              value={argument.ruleInvocation.ruleId}
              onChange={(ruleId) =>
                onArgumentChange({
                  ...argument,
                  ruleInvocation: { ...argument.ruleInvocation, ruleId },
                })
              }
              popup={(show, handleClose, onSelect) => (
                <RuleSelectorPopup
                  projectId={projectId}
                  ruleInterfaceId={ruleInterfaceId}
                  show={show}
                  handleClose={handleClose}
                  onRuleSelected={onSelect}
                />
              )}
              display={(ruleId) => ruleId.id}
            />
            <Button style={{ marginLeft: "15px" }} onClick={() => onArgumentChange({ ...argument })}>
              Refresh
            </Button>
          </div>
          <RuleInvocationEditor
            ruleInvocation={argument.ruleInvocation}
            onRuleInvocationChange={(ruleInvocation) =>
              onArgumentChange({
                ...argument,
                ruleInvocation,
              })
            }
          />
        </div>
      )}
    </ProjectContext.Consumer>
  );
};

export default RuleInvocationArgumentRuleValueEditor;
