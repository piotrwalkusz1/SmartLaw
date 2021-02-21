import Id, { decodeId } from "./Id";
import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import { List } from "immutable";
import Validator, { decodeValidator } from "./Validator";

export const decodeMetaArgument = (json: any): MetaArgument => {
  return {
    name: decodeString(json.name),
    displayName: decodeNullable(json.displayName, decodeString),
    description: decodeNullable(json.description, decodeString),
    type: decodeId(json.type),
    validators: decodeList(json.validators, decodeValidator),
  };
};

export default interface MetaArgument {
  name: string;
  displayName: string | null;
  description: string | null;
  type: Id;
  validators: List<Validator>;
}
