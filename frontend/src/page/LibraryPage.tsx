import React, { useState } from "react";
import Library from "../model/Library";
import * as DocumentService from "../service/DocumentService";
import { useFetchedData } from "../utils/Hooks";
import { Button } from "react-bootstrap";
import RuleListEditor from "../component/rule/RuleListEditor";
import RuleInterfaceListEditor from "../component/rule/RuleInterfaceListEditor";
import ExpandableArea from "../common/ExpandableArea";

const LibraryPage = () => {
  const libraryDbId = "1";
  const [savedLibrary, setSavedLibrary] = useState<Library | undefined>();
  const [library, setLibrary] = useState<Library | undefined>();
  useFetchedData(
    () => DocumentService.getDocument<Library>(libraryDbId),
    (downloadedLibrary) => {
      setLibrary(downloadedLibrary);
      setSavedLibrary(downloadedLibrary);
    },
    []
  );

  const saveLibrary = (library: Library) => {
    DocumentService.saveDocument(libraryDbId, library).then(() => {
      setSavedLibrary(library);
    });
  };

  return library === undefined ? (
    <div />
  ) : (
    <div>
      <div>
        <Button disabled={library === savedLibrary} onClick={() => saveLibrary(library)}>
          Save
        </Button>
      </div>
      <ExpandableArea header="Rules">
        <RuleListEditor rules={library.rules} onRulesChange={(rules) => setLibrary(library.withRules(rules))} />
      </ExpandableArea>
      <ExpandableArea header={"Interfaces"}>
        <RuleInterfaceListEditor
          rulesInterfaces={library.rulesInterfaces}
          onRulesInterfacesChange={(rulesInterfaces) => setLibrary(library.withRulesInterfaces(rulesInterfaces))}
        />
      </ExpandableArea>
    </div>
  );
};

export default LibraryPage;
