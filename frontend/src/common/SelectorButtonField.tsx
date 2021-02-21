import { ReactElement, useState } from "react";
import { Button } from "react-bootstrap";
import TextField from "./TextField";

interface SelectorButtonFieldProps<T> {
  value: T;
  onChange: (value: T) => void;
  popup: (show: boolean, handleClose: () => void, onSelect: (value: T) => void) => ReactElement;
  display: (value: T) => string;
}

const SelectorButtonField = <T,>({ value, onChange, popup, display }: SelectorButtonFieldProps<T>) => {
  let [show, setShow] = useState(false);

  const handleClose = () => setShow(false);

  return (
    <div>
      <div style={{ display: "flex", alignItems: "baseline" }}>
        <TextField value={display(value)} disabled={true} />
        <Button style={{ marginLeft: "15px" }} onClick={() => setShow(true)}>
          Select
        </Button>
      </div>
      {popup(show, handleClose, onChange)}
    </div>
  );
};

export default SelectorButtonField;
