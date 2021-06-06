import axios from "axios";
import Document, { decodeDocument, DocumentType } from "../model/Document";
import Contract from "../model/Contract";
import Library from "../model/Library";
import FileDownload from "js-file-download";

export const getDocument = (documentId: string): Promise<Document> => {
  return axios.get("/documents/" + documentId).then((response) => {
    return decodeDocument(response.data) as Document;
  });
};

export const getContract = (documentId: string): Promise<Contract> => {
  return getDocument(documentId).then((document) => {
    if (document.documentType === DocumentType.Contract) {
      return document as Contract;
    } else {
      throw Error("Expected document type " + DocumentType.Contract + " but was " + document.documentType);
    }
  });
};

export const getLibrary = (documentId: string): Promise<Library> => {
  return getDocument(documentId).then((document) => {
    if (document.documentType === DocumentType.Library) {
      return document as Library;
    } else {
      throw Error("Expected document type " + DocumentType.Library + " but was " + document.documentType);
    }
  });
};

export const getDocumentAsXml = (documentId: string): void => {
  axios.get("/documents/" + documentId + "/xml", { responseType: "blob" }).then((response) => {
    FileDownload(response.data, "Document.xml");
  });
};

export const saveDocument = <T extends Document>(documentId: string, document: T): Promise<void> => {
  return axios.put("/documents/" + documentId, document);
};
