import { List } from "immutable";
import React, { ReactElement, useState } from "react";
import TextField from "./TextField";
import { useFetchedData } from "../utils/Hooks";

interface BrowserProps<T> {
  search: (searchPhrase: string) => Promise<List<T>>;
  display: (item: T) => ReactElement;
  onSelect: (item: T) => void;
}

const Browser = <T,>({ search, display, onSelect }: BrowserProps<T>) => {
  const [searchPhrase, setSearchPhrase] = useState("");
  const [items, setItems] = useState(List<T>());
  useFetchedData(() => search(searchPhrase), setItems, [searchPhrase]);

  return (
    <div>
      <TextField value={searchPhrase} onChange={setSearchPhrase} />
      <div>
        {items.map((item, index) => (
          <div key={index} onClick={() => onSelect(item)}>
            {display(item)}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Browser;
