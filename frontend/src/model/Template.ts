import { decodeStaticTemplate } from "./StaticTemplate";
import { decodeTextEngineTemplate } from "./TextEngineTemplate";

export enum TemplateType {
  Static = "Static",
  TextEngine = "TextEngine",
}

export const decodeTemplate = (json: any): Template => {
  switch (json.templateType) {
    case TemplateType.Static:
      return decodeStaticTemplate(json);
    case TemplateType.TextEngine:
      return decodeTextEngineTemplate(json);
    default:
      throw Error("Template with type " + json.templateType + " is not supported");
  }
};

export default interface Template {
  templateType: TemplateType;
}
