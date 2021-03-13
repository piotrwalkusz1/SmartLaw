import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeEnumVariant = (json: any): EnumVariant => {
  return {
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
  };
};

export default interface EnumVariant {
  name: string;
  description: string | null;
}
