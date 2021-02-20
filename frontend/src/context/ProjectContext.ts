import React from "react";

export interface ProjectContextProps {
  projectId: string;
}

const prepareDefaultValue = (): ProjectContextProps => {
  return { projectId: "1" };
};

export const ProjectContext = React.createContext(prepareDefaultValue());
