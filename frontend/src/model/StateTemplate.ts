import Template, { decodeTemplate, TemplateType } from "./Template";
import { decodeEnum, decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import { decodeId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import { decodeMetaValue } from "./MetaValue";
import { decodeType } from "./Type";
import { prepareStaticTemplate } from "./StaticTemplate";
import { prepareEmptyIdTemplate } from "./IdTemplate";
import { List } from "immutable";
import { prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";
import { prepareEmptyMetaPrimitiveValueTemplate } from "./MetaPrimitiveValueTemplate";
import StateElement from "./StateElement";
import { WrapWithTemplate } from "./WrapWithTemplate";

export const decodeStateTemplate = (json: any): StateTemplate => {
  return {
    templateType: decodeEnum(json.templateType, TemplateType),
    id: decodeTemplate(json.id, decodeId),
    annotations: decodeTemplate(json.annotations, (json) => decodeList(json, decodeAnnotation)),
    name: decodeTemplate(json.name, decodeString),
    description: decodeTemplate(json.description, (json) => decodeNullable(json, decodeString)),
    type: decodeTemplate(json.type, decodeType),
    defaultValue: decodeTemplate(json.defaultValue, (json) => decodeNullable(json, decodeMetaValue)),
  };
};

export interface StateTemplate extends WrapWithTemplate<StateElement> {}

export const prepareEmptyStateTemplate = (): StateTemplate => {
  return {
    templateType: TemplateType.StateTemplate,
    id: prepareEmptyIdTemplate(),
    annotations: prepareStaticTemplate(List()),
    name: prepareStaticTemplate(""),
    description: prepareStaticTemplate(null),
    type: prepareEmptyDefinitionRefTemplate(),
    defaultValue: prepareEmptyMetaPrimitiveValueTemplate(),
  };
};

export const isStateTemplate = <T>(template: Template<T>): template is StateTemplate => {
  return template.templateType === TemplateType.StateTemplate;
};
