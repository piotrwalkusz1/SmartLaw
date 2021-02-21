import Document from "../model/Document";
import axios from "axios";
import PresentationElement from "../model/PresentationElement";
import { List } from "immutable";
import {
  decodeExtendPresentationElementsResultDto,
  ExtendPresentationElementsResultDto,
} from "../model/ExtendPresentationElementsResultDto";
import FileDownload from "js-file-download";
import { decodeList } from "../utils/Decoders";
import Project, { decodeProject } from "../model/Project";

export const downloadDocument = (projectId: string, document: Document): void => {
  axios
    .post("/projects/" + projectId + "/documents/convert/natural-language", { document: document }, { responseType: "blob" })
    .then((response) => {
      FileDownload(response.data, "Contract.docx");
    });
};

export const extendPresentationElements = (
  projectId: string,
  allPresentationElements: List<PresentationElement>,
  presentationElementsToExtend: List<PresentationElement>
): Promise<ExtendPresentationElementsResultDto> => {
  return axios
    .post("/projects/" + projectId + "/documents/extend-presentation-elements", {
      allPresentationElements: allPresentationElements,
      presentationElementsToExtend: presentationElementsToExtend,
    })
    .then((response) => {
      return decodeExtendPresentationElementsResultDto(response.data);
    });
};

export const getProject = (projectId: string) => {
  return axios.get("/projects/" + projectId).then((response) => {
    return decodeProject(response.data);
  });
};

export const createProject = (request: { name: string }): Promise<Project> => {
  return axios.post("/projects", request).then((response) => {
    return decodeProject(response.data);
  });
};

export const searchProjects = (searchPhrase: string) => {
  return axios.post("/projects/search", { searchPhrase: searchPhrase }).then((response) => {
    return decodeList(response.data, decodeProject);
  });
};
