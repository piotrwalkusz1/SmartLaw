import axios from "axios";
import { List } from "immutable";
import Id from "../model/Id";
import MetaValue from "../model/MetaValue";
import { decodeString } from "../utils/Decoders";

export const processTemplate = (
  projectId: string,
  documentId: string,
  ruleId: Id,
  ruleInvocationArguments: List<MetaValue>
): Promise<string> => {
  return axios
    .post("/templates/process", {
      projectId: projectId,
      documentId: documentId,
      ruleId: ruleId,
      arguments: ruleInvocationArguments,
    })
    .then((response) => {
      return decodeString(response.data);
    });
};
