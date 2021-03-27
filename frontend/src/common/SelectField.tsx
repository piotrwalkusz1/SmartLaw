import React from "react";
import { Form } from "react-bootstrap";
import { List } from "immutable";

interface SelectFieldProps<T> {
  label?: string;
  inlineLabel?: boolean;
  value: T | null;
  onChange: (value: T) => void;
  options: List<T>;
  display: (value: T) => string;
}

const SelectField = <T,>({ label, inlineLabel, value, onChange, options, display }: SelectFieldProps<T>) => {
  const selectedIndex = options.findIndex((option) => option === value);

  return (
    <Form.Group>
      <div style={inlineLabel ? { display: "flex", alignItems: "baseline" } : undefined}>
        {label ? (
          <div style={inlineLabel ? { marginRight: "15px" } : undefined}>
            <Form.Label>{label}</Form.Label>
          </div>
        ) : (
          <></>
        )}
        <Form.Control as="select" value={selectedIndex} onChange={(event) => onChange(options.get(parseInt(event.target.value)) as T)}>
          {options.map((option, index) => (
            <option key={index} value={index}>
              {display(option)}
            </option>
          ))}
        </Form.Control>
      </div>
    </Form.Group>
  );
};

export default SelectField;
