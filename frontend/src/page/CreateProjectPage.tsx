import { useState } from "react";
import TextField from "../common/TextField";
import { Button, Container } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import * as ProjectService from "../service/ProjectService";

const CreateProjectPage = () => {
  const [projectName, setProjectName] = useState("");
  const history = useHistory();

  const createProject = () => {
    ProjectService.createProject({ name: projectName }).then((project) => {
      history.push("/projects/" + project.id);
    });
  };

  return (
    <Container style={{ paddingTop: "30px" }}>
      <TextField placeholder="Project name" value={projectName} onChange={setProjectName} />
      <Button onClick={createProject}>Create project</Button>
    </Container>
  );
};

export default CreateProjectPage;
