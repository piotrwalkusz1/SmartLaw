import { decodeNullable, decodeString } from "../utils/Decoders";

export const decodeId = (json: any): Id => {
  return {
    id: decodeString(json.id),
    namespace: decodeNullable(json.namespace, decodeString),
  };
};

export const prepareEmptyId = (): Id => {
  return { id: "", namespace: null };
};

export const areIdsEqual = (firstId: Id, secondId: Id): boolean => {
  return firstId.id === secondId.id && firstId.namespace === secondId.namespace;
};

export default interface Id {
  id: string;
  namespace: string | null;
}
