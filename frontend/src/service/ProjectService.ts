import Document from "../model/Document";
import NaturalLanguageDocument, { decodeNaturalLanguageDocument } from "../model/NaturalLanguageDocument";
import axios from "axios";
import PresentationElement from "../model/PresentationElement";
import { List } from "immutable";
import { decodeList } from "../utils/Decoders";
import { decodeExtendedPresentationElement, ExtendedPresentationElement } from "../model/ExtendedPresentationElement";

export const convertDocumentToNaturalLanguage = (projectId: string, document: Document): Promise<NaturalLanguageDocument> => {
  return axios.post("/projects/" + projectId + "/documents/convert/natural-language", { document: document }).then((response) => {
    return decodeNaturalLanguageDocument(response.data);
  });
};

export const extendPresentationElements = (
  projectId: string,
  presentationElements: List<PresentationElement>
): Promise<List<ExtendedPresentationElement>> => {
  return axios.post("/projects/" + projectId + "/documents/extend-presentation-elements", presentationElements).then((response) => {
    return decodeList(response.data, decodeExtendedPresentationElement);
  });
};
