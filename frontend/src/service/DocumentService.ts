import axios from "axios";
import Document, { decodeDocument } from "../model/Document";

export const getDocument = <T extends Document>(documentId: string): Promise<T> => {
  return axios.get("/documents/" + documentId).then((response) => {
    return decodeDocument(response.data) as T;
  });
};
