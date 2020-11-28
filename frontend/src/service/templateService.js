import axios from 'axios'

export function processTemplate(ruleId, ruleInvocationArguments) {
  return axios.post('/templates/process', {ruleId: ruleId, arguments: ruleInvocationArguments});
}
