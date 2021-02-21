import MetaPrimitiveValue from "../../model/MetaPrimitiveValue";
import React from "react";
import Datetime from "react-datetime";

interface RuleInvocationArgumentLocalDateValueEditorProps {
  argument: MetaPrimitiveValue;
  onArgumentChange: (argument: MetaPrimitiveValue) => void;
}

const RuleInvocationArgumentLocalDateValueEditor = ({ argument, onArgumentChange }: RuleInvocationArgumentLocalDateValueEditorProps) => {
  return (
    <Datetime
      dateFormat={"YYYY-MM-DD"}
      timeFormat={false}
      utc={true}
      value={argument.value}
      onChange={(value) =>
        onArgumentChange({
          ...argument,
          value: typeof value === "string" ? value : value.format("YYYY-MM-DD"),
        })
      }
    />
  );
};

export default RuleInvocationArgumentLocalDateValueEditor;
