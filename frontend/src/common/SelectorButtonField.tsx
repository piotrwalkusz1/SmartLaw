import { ReactElement, useState } from "react";
import { Button } from "react-bootstrap";

interface SelectorButtonFieldProps<T> {
  value: T;
  onChange: (value: T) => void;
  popup: (show: boolean, handleClose: () => void, onSelect: (value: T) => void) => ReactElement;
}

const SelectorButtonField = <T,>({ value, onChange, popup }: SelectorButtonFieldProps<T>) => {
  let [show, setShow] = useState(false);

  const handleClose = () => setShow(false);

  return (
    <div>
      <Button onClick={() => setShow(true)}>Select</Button>
      {popup(show, handleClose, onChange)}
    </div>
  );
};

export default SelectorButtonField;
