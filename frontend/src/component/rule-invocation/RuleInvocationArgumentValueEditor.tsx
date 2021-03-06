import MetaValue from "../../model/MetaValue";
import MetaArgument from "../../model/MetaArgument";
import { areIdsEqual } from "../../model/Id";
import { META_TYPES } from "../../service/MetaTypes";
import RuleInvocationArgumentStringValueEditor from "./RuleInvocationArgumentStringValueEditor";
import MetaPrimitiveValue from "../../model/MetaPrimitiveValue";
import RuleInvocationArgumentIntegerValueEditor from "./RuleInvocationArgumentIntegerValueEditor";
import RuleInvocationArgumentLocalDateValueEditor from "./RuleInvocationArgumentLocalDateValueEditor";
import RuleInvocationArgumentRuleValueEditor from "./RuleInvocationArgumentRuleValueEditor";
import MetaRuleValue from "../../model/MetaRuleValue";

interface RuleInvocationArgumentValueEditorProps {
  ruleInvocationArgument: MetaValue;
  onRuleInvocationArgumentChange: (ruleInvocationArgument: MetaValue) => void;
  ruleArgument: MetaArgument;
}

const RuleInvocationArgumentValueEditor = ({
  ruleInvocationArgument,
  onRuleInvocationArgumentChange,
  ruleArgument,
}: RuleInvocationArgumentValueEditorProps) => {
  if (areIdsEqual(META_TYPES.STRING, ruleArgument.type)) {
    return (
      <RuleInvocationArgumentStringValueEditor
        argument={ruleInvocationArgument as MetaPrimitiveValue}
        onArgumentChange={onRuleInvocationArgumentChange}
      />
    );
  } else if (areIdsEqual(META_TYPES.INTEGER, ruleArgument.type)) {
    return (
      <RuleInvocationArgumentIntegerValueEditor
        argument={ruleInvocationArgument as MetaPrimitiveValue}
        onArgumentChange={onRuleInvocationArgumentChange}
      />
    );
  } else if (areIdsEqual(META_TYPES.LOCAL_DATE, ruleArgument.type)) {
    return (
      <RuleInvocationArgumentLocalDateValueEditor
        argument={ruleInvocationArgument as MetaPrimitiveValue}
        onArgumentChange={onRuleInvocationArgumentChange}
      />
    );
  } else {
    return (
      <RuleInvocationArgumentRuleValueEditor
        argument={ruleInvocationArgument as MetaRuleValue}
        onArgumentChange={onRuleInvocationArgumentChange}
        ruleInterfaceId={ruleArgument.type}
      />
    );
  }
};

export default RuleInvocationArgumentValueEditor;
