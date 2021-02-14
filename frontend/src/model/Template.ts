import { decodeStaticTemplate } from "./StaticTemplate";
import { decodeTextEngineTemplate } from "./TextEngineTemplate";
import { decodeGroovyTemplate } from "./GroovyTemplate";

export enum TemplateType {
  Static = "Static",
  TextEngine = "TextEngine",
  Groovy = "Groovy",
}

export const decodeTemplate = (json: any): Template => {
  switch (json.templateType) {
    case TemplateType.Static:
      return decodeStaticTemplate(json);
    case TemplateType.TextEngine:
      return decodeTextEngineTemplate(json);
    case TemplateType.Groovy:
      return decodeGroovyTemplate(json);
    default:
      throw Error("Template with type " + json.templateType + " is not supported");
  }
};

export default interface Template {
  templateType: TemplateType;
}
