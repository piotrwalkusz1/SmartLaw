import { List } from "immutable";
import { decodeList, decodeString } from "../utils/Decoders";

export const decodeProject = (json: any): Project => {
  return {
    id: decodeString(json.id),
    name: decodeString(json.name),
    documentsIds: decodeList(json.documentsIds, decodeString),
    modulesIds: decodeList(json.modulesIds, decodeString),
  };
};

export default interface Project {
  id: string;
  name: string;
  documentsIds: List<string>;
  modulesIds: List<string>;
}
