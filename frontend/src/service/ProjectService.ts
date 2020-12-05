import Document from "../model/Document";
import NaturalLanguageDocument, { decodeNaturalLanguageDocument } from "../model/NaturalLanguageDocument";
import axios from "axios";

export const convertDocumentToNaturalLanguage = (projectId: string, document: Document): Promise<NaturalLanguageDocument> => {
  return axios.post("/projects/" + projectId + "/documents/convert/natural-language", { document: document }).then((response) => {
    return decodeNaturalLanguageDocument(response.data);
  });
};
