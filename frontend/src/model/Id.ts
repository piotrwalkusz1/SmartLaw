import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeId = (json: any): Id => {
  return {
    id: decodeString(json.id),
    namespace: decodeNullable(json.namespace, decodeString),
  };
};

export default interface Id {
  id: string;
  namespace: string | null;
}
