import { decodeStaticTemplate } from "./StaticTemplate";
import { decodeTextEngineTemplate } from "./TextEngineTemplate";
import { decodeGroovyTemplate } from "./GroovyTemplate";

export enum TemplateType {
  Static = "Static",
  TextEngine = "TextEngine",
  Groovy = "Groovy",
}

export const decodeTemplate = <T>(json: any, decodeTemplateResult: (json: any) => T): Template<T> => {
  switch (json.templateType) {
    case TemplateType.Static:
      return decodeStaticTemplate(json, decodeTemplateResult);
    case TemplateType.TextEngine:
      return decodeTextEngineTemplate(json);
    case TemplateType.Groovy:
      return decodeGroovyTemplate(json);
    default:
      throw Error("Template with type " + json.templateType + " is not supported");
  }
};

export default interface Template<T> {
  templateType: TemplateType;
}
