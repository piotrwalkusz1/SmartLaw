import React from 'react'


export function RuleInvocationArgument(props) {
  const {name, description, type, value, onValueChange} = props;

  function renderRuleInvocationArgumentValue() {
    switch (type) {
      case 'String':
        return <div>{value}</div>;
      default:
        return <div/>;
    }
  }

  return (
    <div>
      <div>{name}</div>
      <div>{description}</div>
      renderRuleInvocationArgumentValue()
    </div>
  );
}