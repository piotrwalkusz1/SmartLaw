import React, {useState} from 'react'
import {List} from 'immutable'
import {RuleInvocation} from "./ruleInvocation";

function Contract(props) {
  const [ruleInvocations, setRuleInvocations] = useState(List());

  return <div>
    {ruleInvocations.map(({ruleId, ruleArguments}) =>
      <RuleInvocation ruleId={ruleId} ruleArguments={ruleArguments}/>
    )}
  </div>
}