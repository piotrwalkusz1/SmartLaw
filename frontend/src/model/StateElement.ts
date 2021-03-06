import Type, { decodeType } from "./Type";
import MetaValue, { decodeMetaValue } from "./MetaValue";
import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Element, { ElementType } from "./Element";
import { decodeId, prepareEmptyId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import { List } from "immutable";
import { prepareDefinitionRef } from "./DefinitionRef";
import { TYPES } from "../service/Types";

export const prepareEmptyStateElement = (): StateElement => {
  return {
    elementType: ElementType.State,
    name: "",
    annotations: List(),
    defaultValue: null,
    description: null,
    id: prepareEmptyId(),
    type: prepareDefinitionRef(TYPES.UINT),
  };
};

export const decodeStateElement = (json: any): StateElement => {
  return {
    elementType: ElementType.State,
    id: decodeId(json.id),
    annotations: decodeList(json.annotations, decodeAnnotation),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    type: decodeType(json.type),
    defaultValue: decodeNullable(json.defaultValue, decodeMetaValue),
  };
};

export default interface StateElement extends Element {
  name: string;
  description: string | null;
  type: Type;
  defaultValue: MetaValue | null;
}
