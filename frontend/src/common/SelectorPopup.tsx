import React, { ReactElement } from "react";
import { List } from "immutable";
import { Modal } from "react-bootstrap";
import Browser from "./Browser";

interface SelectorPopupProps<T> {
  header: string;
  show: boolean;
  handleClose: () => void;
  search: (searchPhrase: string) => Promise<List<T>>;
  onSelect: (selectedResult: T) => void;
  display: (result: T) => ReactElement;
}

const SelectorPopup = <T,>({ header, show, handleClose, search, onSelect, display }: SelectorPopupProps<T>) => {
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{header}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Browser search={search} display={display} onSelect={onSelect} />
      </Modal.Body>
    </Modal>
  );
};

export default SelectorPopup;
