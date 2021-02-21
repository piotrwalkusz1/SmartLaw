import { useHistory } from "react-router-dom";
import Browser from "../common/Browser";
import { searchProjects } from "../service/ProjectService";
import { Button, Container } from "react-bootstrap";

interface ProjectsManagerPageProps {}

const ProjectsManagerPage = ({}: ProjectsManagerPageProps) => {
  let history = useHistory();

  return (
    <Container style={{ paddingTop: "30px" }}>
      <Button onClick={() => history.push("/projects/new")}>Create new project</Button>
      <div style={{ marginTop: "30px" }}>
        <Browser
          placeholder="Search project by name"
          search={searchProjects}
          display={(project) => <div>{project.name}</div>}
          onSelect={(project) => {
            history.push("/projects/" + project.id);
          }}
        />
      </div>
    </Container>
  );
};

export default ProjectsManagerPage;
