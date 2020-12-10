import Document from "../model/Document";
import axios from "axios";
import PresentationElement from "../model/PresentationElement";
import { List } from "immutable";
import {
  decodeExtendPresentationElementsResultDto,
  ExtendPresentationElementsResultDto,
} from "../model/ExtendPresentationElementsResultDto";
import FileDownload from "js-file-download";

export const downloadDocument = (projectId: string, document: Document): void => {
  axios
    .post("/projects/" + projectId + "/documents/convert/natural-language", { document: document }, { responseType: "blob" })
    .then((response) => {
      FileDownload(response.data, "Contract.docx");
    });
};

export const extendPresentationElements = (
  projectId: string,
  presentationElements: List<PresentationElement>
): Promise<ExtendPresentationElementsResultDto> => {
  return axios.post("/projects/" + projectId + "/documents/extend-presentation-elements", presentationElements).then((response) => {
    return decodeExtendPresentationElementsResultDto(response.data);
  });
};
