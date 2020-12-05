import Template from "./Template";
import { decodeState } from "./State";

export enum ElementType {
  State = "State",
}

export const decodeElement = (json: any): Element => {
  switch (json.elementType) {
    case ElementType.State:
      return decodeState(json);
    default:
      throw Error("Element with type " + json.elementType + " is not supported");
  }
};

export default interface Element {
  elementType: ElementType;
  id: Template;
  annotations: Template;
}
