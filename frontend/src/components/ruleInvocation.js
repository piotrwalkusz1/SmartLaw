import React, {useState} from 'react'
import {RuleInvocationArgument} from "./ruleInvocationArgument";

export function RuleInvocation(props) {
  const {ruleId, ruleArguments} = props;

  const [content, setContent] = useState("");
  // TemplateService.processTemplate()

  return (
    <div>
      <div>{content}</div>
      <div>
        {ruleArguments.map(({name, description, type, value}) =>
          <RuleInvocationArgument name={name} description={description} type={type} value={value}/>
        )}
      </div>
    </div>

  );
}