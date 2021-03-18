import Id from "./Id";
import { List } from "immutable";
import Annotation from "./Annotation";
import { decodeStateElement } from "./StateElement";
import { decodeEnum } from "../utils/Decoders";
import { decodeEnumDefinitionElement } from "./EnumDefinitionElement";
import { decodeActionDefinitionElement } from "./ActionDefinitionElement";

export enum ElementType {
  State = "State",
  EnumDefinition = "EnumDefinition",
  ActionDefinition = "ActionDefinition",
}

export const decodeElement = (json: any): Element => {
  const elementType = decodeEnum(json.elementType, ElementType);

  switch (elementType) {
    case ElementType.State:
      return decodeStateElement(json);
    case ElementType.EnumDefinition:
      return decodeEnumDefinitionElement(json);
    case ElementType.ActionDefinition:
      return decodeActionDefinitionElement(json);
  }
};

export default interface Element {
  elementType: ElementType;
  id: Id;
  annotations: List<Annotation>;
}
