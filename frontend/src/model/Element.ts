import Id from "./Id";
import MetaValue from "./MetaValue";
import { List } from "immutable";

export enum ElementType {
  State = "State",
}

export const decodeElement = (json: any): Element => {
  switch (json.elementType) {
    default:
      throw Error("Element with type " + json.elementType + " is not supported");
  }
};

export default interface Element {
  elementType: ElementType;
  id: Id;
  annotations: List<MetaValue>;
}
