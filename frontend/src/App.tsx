import React from "react";
import "./App.css";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import "react-datetime/css/react-datetime.css";
import RootPage from "./page/RootPage";

function App() {
  return (
    <React.StrictMode>
      <BrowserRouter>
        <Switch>
          <Route path="/">
            <RootPage />
          </Route>
        </Switch>
      </BrowserRouter>
    </React.StrictMode>
  );
}

export default App;
