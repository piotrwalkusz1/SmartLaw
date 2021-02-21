/** @jsxImportSource @emotion/react */
import { List } from "immutable";
import React, { ReactElement, useState } from "react";
import TextField from "./TextField";
import { useFetchedData } from "../utils/Hooks";
import { Card } from "react-bootstrap";
import { css } from "@emotion/react";

const Styles = {
  item: css`
    cursor: pointer;

    &:hover {
      background-color: lightgray;
    }
  `,
};

interface BrowserProps<T> {
  search: (searchPhrase: string) => Promise<List<T>>;
  display: (item: T) => ReactElement;
  onSelect: (item: T) => void;
  placeholder?: string;
}

const Browser = <T,>({ search, display, onSelect, placeholder }: BrowserProps<T>) => {
  const [searchPhrase, setSearchPhrase] = useState("");
  const [items, setItems] = useState(List<T>());
  useFetchedData(() => search(searchPhrase), setItems, [searchPhrase]);

  return (
    <div>
      <TextField placeholder={placeholder} value={searchPhrase} onChange={setSearchPhrase} />
      <div>
        {items.map((item, index) => (
          <Card body key={index} css={Styles.item} onClick={() => onSelect(item)}>
            {display(item)}
          </Card>
        ))}
      </div>
    </div>
  );
};

export default Browser;
