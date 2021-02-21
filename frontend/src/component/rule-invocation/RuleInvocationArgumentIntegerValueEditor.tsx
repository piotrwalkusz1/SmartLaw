import MetaPrimitiveValue from "../../model/MetaPrimitiveValue";
import React from "react";
import NumberField from "../../common/NumberField";

interface RuleInvocationArgumentIntegerValueEditorProps {
  argument: MetaPrimitiveValue;
  onArgumentChange: (argument: MetaPrimitiveValue) => void;
}

const RuleInvocationArgumentIntegerValueEditor = ({ argument, onArgumentChange }: RuleInvocationArgumentIntegerValueEditorProps) => {
  return <NumberField value={Number(argument.value)} onChange={(value) => onArgumentChange({ ...argument, value: String(value) })} />;
};

export default RuleInvocationArgumentIntegerValueEditor;
