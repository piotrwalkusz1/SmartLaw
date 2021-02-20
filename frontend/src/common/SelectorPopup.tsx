import React, { ReactElement, useState } from "react";
import { List } from "immutable";
import { useFetchedData } from "../utils/Hooks";
import { Modal } from "react-bootstrap";
import TextField from "./TextField";

interface SelectorPopupProps<T> {
  header: string;
  show: boolean;
  handleClose: () => void;
  search: (searchPhrase: string) => Promise<List<T>>;
  onSelect: (selectedResult: T) => void;
  display: (result: T) => ReactElement;
}

const SelectorPopup = <T,>({ header, show, handleClose, search, onSelect, display }: SelectorPopupProps<T>) => {
  const [searchPhrase, setSearchPhrase] = useState("");
  const [results, setResults] = useState(List<T>());
  useFetchedData(() => search(searchPhrase), setResults, [searchPhrase]);

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{header}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <TextField value={searchPhrase} onChange={setSearchPhrase} />
        <div>
          {results.map((result, index) => (
            <div key={index} onClick={() => onSelect(result)}>
              {display(result)}
            </div>
          ))}
        </div>
      </Modal.Body>
    </Modal>
  );
};

export default SelectorPopup;
