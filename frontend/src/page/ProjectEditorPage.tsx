import { ProjectContext } from "../context/ProjectContext";
import { Container, Tab, Tabs } from "react-bootstrap";
import ContractPage from "./ContractPage";
import LibraryPage from "./LibraryPage";
import React, { useState } from "react";
import { useFetchedData } from "../utils/Hooks";
import Project from "../model/Project";
import { getProject } from "../service/ProjectService";
import { useParams } from "react-router-dom";

interface ParamTypes {
  projectId: string;
}

const ProjectEditorPage = () => {
  const { projectId } = useParams<ParamTypes>();
  const [project, setProject] = useState<Project | null>(null);
  useFetchedData(() => getProject(projectId), setProject, [projectId]);

  if (project) {
    return (
      <ProjectContext.Provider value={{ projectId: projectId }}>
        <Container style={{ maxWidth: "100%", paddingTop: "15px" }}>
          <Tabs defaultActiveKey="contract">
            <Tab eventKey="contract" title="Contract">
              <ContractPage contractDbId={project.documentsIds.get(0) || ""} />
            </Tab>
            <Tab eventKey="library" title="Library">
              <LibraryPage libraryDbId={project.documentsIds.get(1) || ""} />
            </Tab>
          </Tabs>
        </Container>
      </ProjectContext.Provider>
    );
  } else {
    return <> </>;
  }
};

export default ProjectEditorPage;
