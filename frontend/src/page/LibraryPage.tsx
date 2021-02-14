import React, { useState } from "react";
import Library from "../model/Library";
import * as DocumentService from "../service/DocumentService";
import { useFetchedData } from "../utils/Hooks";
import RuleEditor from "../component/rule/RuleEditor";
import { Button } from "react-bootstrap";

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
      <div>
        {library.rules.map((rule, index) => (
          <RuleEditor key={index} rule={rule} onRuleChange={(rule) => setLibrary(library.withRule(index, rule))} />
        ))}
      </div>
    </div>
  );
};

export default LibraryPage;
