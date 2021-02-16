import Id from "./Id";
import { List } from "immutable";
import Annotation from "./Annotation";
import { decodeStateElement } from "./StateElement";

export enum ElementType {
  State = "State",
}

export const decodeElement = (json: any): Element => {
  switch (json.elementType) {
    case ElementType.State:
      return decodeStateElement(json);
    default:
      throw Error("Element with type " + json.elementType + " is not supported");
  }
};

export default interface Element {
  elementType: ElementType;
  id: Id;
  annotations: List<Annotation>;
}
