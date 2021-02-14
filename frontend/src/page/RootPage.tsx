import React from "react";
import { Container, Tab, Tabs } from "react-bootstrap";
import ContractPage from "./ContractPage";
import LibraryPage from "./LibraryPage";

const RootPage = () => {
  return (
    <Container style={{ maxWidth: "100%" }}>
      <Tabs defaultActiveKey="contract">
        <Tab eventKey="contract" title="Contract">
          <ContractPage />
        </Tab>
        <Tab eventKey="library" title="Library">
          <LibraryPage />
        </Tab>
      </Tabs>
    </Container>
    // <div style={{ display: "flex", flexDirection: "column", height: "600px" }}>
    //   <div>Top</div>
    //   <div style={{ flexGrow: 1, overflowY: "auto" }}>
    //     <div style={{ height: "600px" }}>Bottom 1</div>
    //     <div style={{ height: "200px" }}>Bottom 2</div>
    //   </div>
    // </div>
  );
};

export default RootPage;
