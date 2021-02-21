import { List } from "immutable";
import RuleInvocationPresentationView from "./RuleInvocationPresentationView";
import React, { useContext, useState } from "react";
import RuleInvocation from "../../model/RuleInvocation";
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
  const [extendedPresentationElement, setExtendedPresentationElement] = useState<ExtendedRuleInvocationPresentationElement | null>(null);
  const projectContext = useContext(ProjectContext);
  useFetchedData(() => fetchExtendedPresentationElement(projectContext.projectId, ruleInvocation), setExtendedPresentationElement, [
    ruleInvocation,
  ]);

  return (
    <div>
      {extendedPresentationElement ? (
        <RuleInvocationPresentationView
          ruleInvocation={ruleInvocation}
          onRuleInvocationChange={onRuleInvocationChange}
          rule={extendedPresentationElement.rule}
          ruleContent={extendedPresentationElement.naturalLanguageDocumentObject.content}
          validationResults={extendedPresentationElement.validationResults}
        />
      ) : (
        <> </>
      )}
    </div>
  );
};

export default RuleInvocationEditor;
