import Type, { decodeType } from "./Type";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import DefinitionRef from "./DefinitionRef";
import { prepareEmptyIdTemplate } from "./IdTemplate";
import { prepareEmptyListTemplate } from "./ListTemplate";

export const decodeDefinitionRefTemplate = (json: any): DefinitionRefTemplate => {
  return {
    templateType: TemplateType.DefinitionRefTemplate,
    definition: decodeTemplate(json.definition, decodeId),
    parameters: decodeTemplate(json.parameters, (json) => decodeList(json, decodeType)),
  };
};

export default interface DefinitionRefTemplate extends Template<DefinitionRef> {
  definition: Template<Id>;
  parameters: Template<List<Type>>;
}

export const prepareEmptyDefinitionRefTemplate = (): DefinitionRefTemplate => {
  return {
    templateType: TemplateType.DefinitionRefTemplate,
    definition: prepareEmptyIdTemplate(),
    parameters: prepareEmptyListTemplate(),
  };
};

export const isDefinitionRefTemplate = <T>(template: Template<Type>): template is DefinitionRefTemplate => {
  return template.templateType === TemplateType.DefinitionRefTemplate;
};
