import { decodeEnum } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import Id, { decodeId, prepareEmptyId } from "./Id";
import { prepareEmptyIdTemplate } from "./IdTemplate";

export default interface StateVariableRef {
  state: Id;
}

export const decodeStateVariableRef = (json: any): StateVariableRef => {
  return {
    state: decodeId(json.state),
  };
};

export const prepareEmptyStateVariableRef = (): StateVariableRef => {
  return {
    state: prepareEmptyId(),
  };
};

export interface StateVariableRefTemplate extends WrapWithTemplate<StateVariableRef> {}

export const decodeStateVariableRefTemplate = (json: any): StateVariableRefTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    state: decodeTemplate(json.state, decodeId),
  };
};

export const prepareEmptyStateVariableRefTemplate = (): StateVariableRefTemplate => {
  return {
    templateType: TemplateType.StateVariableRefTemplate,
    state: prepareEmptyIdTemplate(),
  };
};

export const isStateVariableRefTemplate = <T>(template: Template<T>): template is StateVariableRefTemplate => {
  return template.templateType === TemplateType.StateVariableRefTemplate;
};
