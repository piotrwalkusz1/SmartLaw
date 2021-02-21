import MetaPrimitiveValue from "../../model/MetaPrimitiveValue";
import React from "react";
import TextField from "../../common/TextField";

interface RuleInvocationArgumentStringValueEditorProps {
  argument: MetaPrimitiveValue;
  onArgumentChange: (argument: MetaPrimitiveValue) => void;
}

const RuleInvocationArgumentStringValueEditor = ({ argument, onArgumentChange }: RuleInvocationArgumentStringValueEditorProps) => {
  return <TextField value={argument.value} onChange={(value) => onArgumentChange({ ...argument, value })} />;
};

export default RuleInvocationArgumentStringValueEditor;
