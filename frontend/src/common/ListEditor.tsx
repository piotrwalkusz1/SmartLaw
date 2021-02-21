import { List } from "immutable";
import React, { ReactElement } from "react";
import { Accordion, Button, Card } from "react-bootstrap";
import AddButton from "./AddButton";

interface ListEditorProps<T> {
  items: List<T>;
  onItemsChange: (items: List<T>) => void;
  header: (item: T) => string;
  content: (item: T, onItemChange: (item: T) => void) => ReactElement;
  emptyItem?: () => T;
  onItemAdd?: () => void;
  allowRemove?: boolean;
}

const ListEditor = <T,>({ items, onItemsChange, header, content, emptyItem, onItemAdd, allowRemove }: ListEditorProps<T>) => {
  const handleItemAdd = onItemAdd ? onItemAdd : emptyItem ? () => onItemsChange(items.push(emptyItem())) : null;

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
                <div>
                  {allowRemove ? <Button onClick={() => onItemsChange(items.remove(index))}>Remove</Button> : <> </>}
                  <Card.Body>{content(item, (item) => onItemsChange(items.set(index, item)))}</Card.Body>
                </div>
              </Accordion.Collapse>
            </Card>
          </Accordion>
        ))}
      </div>
      {handleItemAdd ? <AddButton onClick={handleItemAdd} /> : <></>}
    </div>
  );
};

export default ListEditor;
