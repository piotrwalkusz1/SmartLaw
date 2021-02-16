import Id, { decodeId } from "./Id";
import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeRuleInterface = (json: any): RuleInterface => {
  return {
    id: decodeId(json.id),
    name: decodeString(json.name),
    description: decodeNullable(json.description, decodeString),
  };
};

export default interface RuleInterface {
  id: Id;
  name: string;
  description: string | null;
}
