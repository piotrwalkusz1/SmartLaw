import { List } from "immutable";
import React, { ReactElement } from "react";
import { Accordion, Card } from "react-bootstrap";
import AddButton from "./AddButton";

interface ListEditorProps<T> {
  items: List<T>;
  onItemsChange: (items: List<T>) => void;
  header: (item: T) => string;
  content: (item: T, onItemChange: (item: T) => void) => ReactElement;
  emptyItem: () => T;
}

const ListEditor = <T,>({ items, onItemsChange, header, content, emptyItem }: ListEditorProps<T>) => {
  return (
    <div>
      <div>
        {items.map((item, index) => (
          <Accordion key={index}>
            <Card>
              <Accordion.Toggle as={Card.Header} eventKey="0">
                {header(item)}
              </Accordion.Toggle>
              <Accordion.Collapse eventKey="0">
                <Card.Body>{content(item, (item) => onItemsChange(items.set(index, item)))}</Card.Body>
              </Accordion.Collapse>
            </Card>
          </Accordion>
        ))}
      </div>
      <AddButton onClick={() => onItemsChange(items.push(emptyItem()))} />
    </div>
  );
};

export default ListEditor;
