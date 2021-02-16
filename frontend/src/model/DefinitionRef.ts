import Type, { decodeType, TypeKind } from "./Type";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";

export const decodeDefinitionRef = (json: any): DefinitionRef => {
  return {
    type: TypeKind.DefinitionRef,
    definition: decodeId(json.definition),
    parameters: decodeList(json.parameters, decodeType),
  };
};

export const prepareDefinitionRef = (definition: Id): DefinitionRef => {
  return {
    type: TypeKind.DefinitionRef,
    definition: definition,
    parameters: List(),
  };
};

export default interface DefinitionRef extends Type {
  definition: Id;
  parameters: List<Type>;
}
