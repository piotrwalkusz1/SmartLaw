import React, { useContext, useState } from "react";
import Library from "../model/Library";
import * as DocumentService from "../service/DocumentService";
import { useFetchedData } from "../utils/Hooks";
import { Button } from "react-bootstrap";
import RuleListEditor from "../component/rule/RuleListEditor";
import RuleInterfaceListEditor from "../component/rule/RuleInterfaceListEditor";
import ExpandableArea from "../common/ExpandableArea";
import Id from "../model/Id";
import { List } from "immutable";
import { getRulesArgumentsTypes } from "../service/RuleService";
import { ProjectContext } from "../context/ProjectContext";

const LibraryPage = ({ libraryDbId }: { libraryDbId: string }) => {
  const { projectId } = useContext(ProjectContext);
  const [savedLibrary, setSavedLibrary] = useState<Library | undefined>();
  const [library, setLibrary] = useState<Library | undefined>();
  const [rulesArgumentsTypes, setRulesArgumentsTypes] = useState<List<Id> | undefined>(undefined);
  useFetchedData(
    () => DocumentService.getLibrary(libraryDbId),
    (downloadedLibrary) => {
      setLibrary(downloadedLibrary);
      setSavedLibrary(downloadedLibrary);
    },
    []
  );
  useFetchedData(() => getRulesArgumentsTypes({ projectId }), setRulesArgumentsTypes, [savedLibrary]);

  const saveLibrary = (library: Library) => {
    DocumentService.saveDocument(libraryDbId, library).then(() => {
      setSavedLibrary(library);
    });
  };

  return library === undefined || rulesArgumentsTypes === undefined ? (
    <div />
  ) : (
    <div>
      <div>
        <Button style={{ margin: "15px 0" }} disabled={library === savedLibrary} onClick={() => saveLibrary(library)}>
          Save
        </Button>
      </div>
      <ExpandableArea header="Rules">
        <RuleListEditor
          projectId={projectId}
          rules={library.rules}
          onRulesChange={(rules) => setLibrary(library.withRules(rules))}
          ruleArgumentTypes={rulesArgumentsTypes}
        />
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
