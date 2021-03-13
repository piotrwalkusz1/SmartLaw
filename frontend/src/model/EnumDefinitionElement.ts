import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Element, { ElementType } from "./Element";
import { decodeId, prepareEmptyId } from "./Id";
import { decodeAnnotation } from "./Annotation";
import { List } from "immutable";
import EnumVariant, { decodeEnumVariant } from "./EnumVariant";

export const prepareEmptyEnumDefinitionElement = (): EnumDefinitionElement => {
  return {
    elementType: ElementType.EnumDefinition,
    id: prepareEmptyId(),
    annotations: List(),
    name: "",
    description: null,
    variants: List(),
  };
};

export const decodeEnumDefinitionElement = (json: any): EnumDefinitionElement => {
  return {
    elementType: ElementType.EnumDefinition,
    id: decodeId(json.id),
    annotations: decodeList(json.annotations, decodeAnnotation),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
    variants: decodeList(json.variants, decodeEnumVariant),
  };
};

export default interface EnumDefinitionElement extends Element {
  name: string;
  description: string | null;
  variants: List<EnumVariant>;
}
