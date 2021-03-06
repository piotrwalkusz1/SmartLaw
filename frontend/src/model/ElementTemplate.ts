import Template from "./Template";
import { decodeStateTemplate } from "./StateTemplate";
import Id from "./Id";
import { List } from "immutable";
import Annotation from "./Annotation";

export enum ElementTemplateType {
  State = "State",
}

export const decodeElementTemplate = (json: any): ElementTemplate => {
  switch (json.elementType) {
    case ElementTemplateType.State:
      return decodeStateTemplate(json);
    default:
      throw Error("Element template with type " + json.elementType + " is not supported");
  }
};

export default interface ElementTemplate {
  elementType: ElementTemplateType;
  id: Template<Id>;
  annotations: Template<List<Annotation>>;
}
