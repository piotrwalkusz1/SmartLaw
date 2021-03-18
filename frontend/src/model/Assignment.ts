import { decodeEnum, decodeNullable, decodeString } from "../utils/Decoders";
import DefinitionRef, { decodeDefinitionRef, prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";

export default interface Assignment {
  variable: VariableRef;
  value: Expression;
}

export const decodeAssignment = (json: any): Assignment => {
  return {
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    type: decodeDefinitionRef(json.type),
  };
};

export const prepareEmptyAssignment = (): Assignment => {
  return {
    name: "",
    description: null,
    type: prepareDefinitionRef(TYPES.UINT),
  };
};

export interface AssignmentTemplate extends WrapWithTemplate<Assignment> {}

export const decodeAssignmentTemplate = (json: any): AssignmentTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeDefinitionRef),
  };
};

export const prepareEmptyAssignmentTemplate = (): AssignmentTemplate => {
  return {
    templateType: TemplateType.AssignmentTemplate,
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    type: prepareEmptyDefinitionRefTemplate(),
  };
};

export const isAssignmentTemplate = <T>(template: Template<T>): template is AssignmentTemplate => {
  return template.templateType === TemplateType.AssignmentTemplate;
};
