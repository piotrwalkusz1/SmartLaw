import React from "react";
import Switch from "react-bootstrap/Switch";
import { BrowserRouter, Route } from "react-router-dom";
import ProjectsManagerPage from "./ProjectsManagerPage";
import ProjectEditorPage from "./ProjectEditorPage";
import CreateProjectPage from "./CreateProjectPage";

const RootPage = () => {
  return (
    <BrowserRouter>
      <Switch style={{ paddingLeft: "0" }}>
        <Route exact path="/">
          <ProjectsManagerPage />
        </Route>
        <Route exact path="/projects/new">
          <CreateProjectPage />
        </Route>
        <Route path="/projects/:projectId">
          <ProjectEditorPage />
        </Route>
      </Switch>
    </BrowserRouter>
  );
};

export default RootPage;
