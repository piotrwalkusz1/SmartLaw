import React from "react";
import "./App.css";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import ContractPage from "./page/ContractPage";

function App() {
  return (
    <React.StrictMode>
      <BrowserRouter>
        <Switch>
          <Route path="/">
            <ContractPage />
          </Route>
        </Switch>
      </BrowserRouter>
    </React.StrictMode>
  );
}

export default App;
