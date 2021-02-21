/** @jsxImportSource @emotion/react */
import { List } from "immutable";
import React, { ReactElement } from "react";
import { Accordion, Card } from "react-bootstrap";
import AddButton from "./AddButton";
import { Trash } from "react-bootstrap-icons";
import { css } from "@emotion/react";

const Styles = {
  removeIcon: css`
    visibility: hidden;

    div:hover > div > div > & {
      visibility: visible;
    }

    &:hover {
      color: red;
    }
  `,
};

interface ListEditorProps<T> {
  items: List<T>;
  onItemsChange: (items: List<T>) => void;
  header: (item: T, index: number) => string;
  content: (item: T, onItemChange: (item: T) => void) => ReactElement;
  emptyItem?: () => T;
  onItemAdd?: () => void;
  allowRemove?: boolean;
}

const ListEditor = <T,>({ items, onItemsChange, header, content, emptyItem, onItemAdd, allowRemove }: ListEditorProps<T>) => {
  const handleItemAdd = onItemAdd ? onItemAdd : emptyItem ? () => onItemsChange(items.push(emptyItem())) : null;

  const renderRemoveIcon = (index: number) => {
    if (allowRemove) {
      return (
        <span css={Styles.removeIcon} onClick={() => onItemsChange(items.remove(index))}>
          <Trash />
        </span>
      );
    } else {
      return <> </>;
    }
  };

  return (
    <div>
      <div>
        {items.map((item, index) => (
          <Accordion key={index}>
            <Card>
              <Accordion.Toggle as={Card.Header} eventKey="0">
                <div style={{ display: "flex" }}>
                  <div style={{ flexGrow: 1 }}>{header(item, index)}</div>
                  <div>{renderRemoveIcon(index)}</div>
                </div>
              </Accordion.Toggle>
              <Accordion.Collapse eventKey="0">
                <Card.Body>{content(item, (item) => onItemsChange(items.set(index, item)))}</Card.Body>
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
