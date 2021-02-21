import { List } from "immutable";
import RuleInvocationPresentationView from "./RuleInvocationPresentationView";
import React, { useContext, useState } from "react";
import RuleInvocation from "../../model/RuleInvocation";
import { Button } from "react-bootstrap";
import { ExtendedRuleInvocationPresentationElement } from "../../model/ExtendedRuleInvocationPresentationElement";
import { useFetchedData } from "../../utils/Hooks";
import { extendPresentationElements } from "../../service/ProjectService";
import { ProjectContext } from "../../context/ProjectContext";
import RuleInvocationPresentationElement from "../../model/RuleInvocationPresentationElement";

interface RuleInvocationEditorProps {
  ruleInvocation: RuleInvocation;
  onRuleInvocationChange: (ruleInvocation: RuleInvocation) => void;
}

const fetchExtendedPresentationElement = (
  projectId: string,
  ruleInvocation: RuleInvocation
): Promise<ExtendedRuleInvocationPresentationElement> => {
  let presentationElements = List([new RuleInvocationPresentationElement(ruleInvocation)]);
  return extendPresentationElements(projectId, presentationElements, presentationElements).then((result) => {
    let extendedPresentationElement = result.extendedPresentationElements.get(0);
    if (extendedPresentationElement instanceof ExtendedRuleInvocationPresentationElement) {
      return extendedPresentationElement;
    } else {
      return Promise.reject();
    }
  });
};

const RuleInvocationEditor = ({ ruleInvocation, onRuleInvocationChange }: RuleInvocationEditorProps) => {
  const [refresh, setRefresh] = useState(0);
  const [extendedPresentationElement, setExtendedPresentationElement] = useState<ExtendedRuleInvocationPresentationElement | null>(null);
  const projectContext = useContext(ProjectContext);
  useFetchedData(() => fetchExtendedPresentationElement(projectContext.projectId, ruleInvocation), setExtendedPresentationElement, [
    refresh,
  ]);

  return extendedPresentationElement ? (
    <div>
      <Button onClick={() => setRefresh(refresh + 1)}>Refresh</Button>
      <RuleInvocationPresentationView
        ruleInvocation={ruleInvocation}
        onRuleInvocationChange={onRuleInvocationChange}
        rule={extendedPresentationElement.rule}
        ruleContent={extendedPresentationElement.naturalLanguageDocumentObject.content}
        validationResults={extendedPresentationElement.validationResults}
      />
    </div>
  ) : (
    <></>
  );
};

export default RuleInvocationEditor;
