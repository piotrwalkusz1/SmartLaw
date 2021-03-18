import { decodeStaticTemplate, prepareEmptyStaticTemplate } from "./StaticTemplate";
import { decodeTextEngineTemplate, prepareEmptyTextEngineTemplate } from "./TextEngineTemplate";
import { decodeGroovyTemplate, prepareEmptyGroovyTemplate } from "./GroovyTemplate";
import { decodeListTemplate, prepareEmptyListTemplate } from "./ListTemplate";
import { decodeIdTemplate, prepareEmptyIdTemplate } from "./IdTemplate";
import { decodeEnum } from "../utils/Decoders";
import { decodeStateTemplate, prepareEmptyStateTemplate } from "./StateTemplate";
import { decodeDefinitionRefTemplate, prepareEmptyDefinitionRefTemplate } from "./DefinitionRefTemplate";
import { decodeMetaPrimitiveValueTemplate, prepareEmptyMetaPrimitiveValueTemplate } from "./MetaPrimitiveValueTemplate";
import { decodeEnumDefinitionTemplate, prepareEmptyEnumDefinitionTemplate } from "./EnumDefinitionTemplate";
import { decodeEnumVariantTemplate, prepareEmptyEnumVariantTemplate } from "./EnumVariantTemplate";
import { buildInheritanceMeta, buildMeta, enumMeta, stringMeta } from "../utils/Reflection";

export enum TemplateType {
  Static = "Static",
  TextEngine = "TextEngine",
  Groovy = "Groovy",
  ListTemplate = "ListTemplate",
  IdTemplate = "IdTemplate",
  StateTemplate = "StateTemplate",
  EnumDefinitionTemplate = "EnumDefinitionTemplate",
  DefinitionRefTemplate = "DefinitionRefTemplate",
  EnumVariantTemplate = "EnumVariantTemplate",
  MetaPrimitiveValueTemplate = "MetaPrimitiveValueTemplate",
  ActionArgumentTemplate = "ActionArgumentTemplate",
  FunctionRefTemplate = "FunctionRefTemplate",
  ActionDefinitionTemplate = "ActionDefinitionTemplate",
  GenericParameterTemplate = "GenericParameterTemplate",
  FunctionArgumentDefinitionTemplate = "FunctionArgumentDefinitionTemplate",
  EnumValueTemplate = "EnumValueTemplate",
  StateVariableRefTemplate = "StateVariableRefTemplate",
  FunctionCallTemplate = "FunctionCallTemplate",
}

export const decodeTemplate = <T, R extends T>(json: any, decodeTemplateResult: (json: any) => R): Template<T> => {
  const templateType = decodeEnum(json.templateType, TemplateType);

  switch (templateType) {
    case TemplateType.Static:
      return decodeStaticTemplate(json, decodeTemplateResult);
    case TemplateType.TextEngine:
      return decodeTextEngineTemplate(json);
    case TemplateType.Groovy:
      return decodeGroovyTemplate(json);
    case TemplateType.ListTemplate:
      return decodeListTemplate(json, decodeTemplateResult);
    case TemplateType.IdTemplate:
      return decodeIdTemplate(json);
    case TemplateType.StateTemplate:
      return decodeStateTemplate(json);
    case TemplateType.DefinitionRefTemplate:
      return decodeDefinitionRefTemplate(json);
    case TemplateType.MetaPrimitiveValueTemplate:
      return decodeMetaPrimitiveValueTemplate(json);
    case TemplateType.EnumDefinitionTemplate:
      return decodeEnumDefinitionTemplate(json);
    case TemplateType.EnumVariantTemplate:
      return decodeEnumVariantTemplate(json);
  }
};

export default interface Template<T> {
  templateType: TemplateType;
}

export const prepareEmptyTemplate = (templateType: TemplateType): Template<any> => {
  switch (templateType) {
    case TemplateType.TextEngine:
      return prepareEmptyTextEngineTemplate();
    case TemplateType.Static:
      return prepareEmptyStaticTemplate();
    case TemplateType.Groovy:
      return prepareEmptyGroovyTemplate();
    case TemplateType.ListTemplate:
      return prepareEmptyListTemplate();
    case TemplateType.IdTemplate:
      return prepareEmptyIdTemplate();
    case TemplateType.StateTemplate:
      return prepareEmptyStateTemplate();
    case TemplateType.DefinitionRefTemplate:
      return prepareEmptyDefinitionRefTemplate();
    case TemplateType.MetaPrimitiveValueTemplate:
      return prepareEmptyMetaPrimitiveValueTemplate();
    case TemplateType.EnumVariantTemplate:
      return prepareEmptyEnumVariantTemplate();
    case TemplateType.EnumDefinitionTemplate:
      return prepareEmptyEnumDefinitionTemplate();
  }
};

export const templateMeta = buildInheritanceMeta<Template<any>>("templateType", {
  abc: stringMeta(),
});
