import React, { useEffect, useState } from "react";
import { Card, Form } from "react-bootstrap";
import { Draggable, Droppable } from "react-beautiful-dnd";
import Id from "../model/Id";
import { searchRules } from "../service/RuleService";
import { List } from "immutable";
import Rule from "../model/Rule";

export interface RuleBrowserProps {
  projectId: string;
  searchResult: List<Rule>;
  onSearchResultChange: (searchResult: List<Rule>) => void;
}

const convertIdToString = (id: Id): string => {
  if (id.namespace) {
    return id.namespace + "." + id.id;
  } else {
    return id.id;
  }
};

const RuleBrowser = ({ projectId, searchResult, onSearchResultChange }: RuleBrowserProps) => {
  const [searchPhrase, setSearchPhrase] = useState("");
  useEffect(() => {
    let isSubscribed = true;
    searchRules({ searchPhrase, projectId }).then((rules) => {
      if (isSubscribed) {
        onSearchResultChange(rules);
      }
    });

    return () => {
      isSubscribed = false;
    };
  }, [searchPhrase]);

  return (
    <div>
      <div style={{ marginBottom: "15px" }}>
        <Form.Control
          type="text"
          placeholder="Search phrase"
          value={searchPhrase}
          onChange={(event) => setSearchPhrase(event.target.value)}
        />
      </div>
      <div style={{ height: "400px", overflowY: "auto" }}>
        <Droppable droppableId="search" type="nested">
          {(provided, snapshot) => (
            <div {...provided.droppableProps} ref={provided.innerRef}>
              {searchResult.map((rule, index) => (
                <Draggable key={convertIdToString(rule.id)} draggableId={convertIdToString(rule.id)} index={index}>
                  {(provided, snapshot) => (
                    <div ref={provided.innerRef} {...provided.draggableProps} style={provided.draggableProps.style}>
                      <div {...provided.dragHandleProps}>
                        <Card body>{rule.name}</Card>
                      </div>
                    </div>
                  )}
                </Draggable>
              ))}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </div>
    </div>
  );
};

export default RuleBrowser;
