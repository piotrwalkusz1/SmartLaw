import { decodeNullable, decodeString } from "../utils/Decoders";
import Template, { decodeTemplate, TemplateType } from "./Template";
import { prepareStaticTemplate } from "./StaticTemplate";
import {buildMeta, enumMeta, nullableMeta, stringMeta} from "../utils/Reflection";
import { WrapWithTemplate } from "./WrapWithTemplate";

export default interface Id {
  id: string;
  namespace: string | null;
}

export const idMeta = buildMeta<Id>({
  id: stringMeta(),
  namespace: nullableMeta(stringMeta()),
});

export const decodeId = idMeta.decodeOrException;

export const prepareEmptyId = idMeta.create();

export const areIdsEqual = (firstId: Id, secondId: Id): boolean => {
  return firstId.id === secondId.id && firstId.namespace === secondId.namespace;
};

export interface IdTemplate extends WrapWithTemplate<Id> {}

export const idTemplateMeta = buildMeta<IdTemplate>({
  templateType: enumMeta(TemplateType),
  id:
})

export const decodeIdTemplate = (json: any): IdTemplate => {
  return {
    templateType: TemplateType.IdTemplate,
    id: decodeTemplate(json.id, decodeString),
    namespace: decodeTemplate(json.namespace, (json) => decodeNullable(json, decodeString)),
  };
};

export const prepareEmptyIdTemplate = (): IdTemplate => {
  return {
    templateType: TemplateType.IdTemplate,
    id: prepareStaticTemplate(""),
    namespace: prepareStaticTemplate(null),
  };
};

export const isIdTemplate = <T>(template: Template<Id>): template is IdTemplate => {
  return template.templateType === TemplateType.IdTemplate;
};
