import React from "react";

export interface DocumentEditorContextProps {
  projectId: string;
  documentId: string;
}

export const DocumentEditorContext = React.createContext(null as DocumentEditorContextProps | null);
