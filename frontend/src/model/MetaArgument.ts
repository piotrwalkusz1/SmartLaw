import Id, { decodeId } from "./Id";
import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeMetaArgument = (json: any): MetaArgument => {
  return {
    name: decodeString(json.name),
    displayName: decodeNullable(json.displayName, decodeString),
    description: decodeNullable(json.description, decodeString),
    type: decodeId(json.type),
  };
};

export default interface MetaArgument {
  name: string;
  displayName: string | null;
  description: string | null;
  type: Id;
}
