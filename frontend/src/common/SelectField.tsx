import React from "react";
import { Form } from "react-bootstrap";
import { List } from "immutable";

interface SelectFieldProps<T> {
  label: string;
  value: T | null;
  onChange: (value: T) => void;
  options: List<T>;
  display: (value: T) => string;
}

const SelectField = <T,>({ label, value, onChange, options, display }: SelectFieldProps<T>) => {
  const selectedIndex = options.findIndex((option) => option === value);

  return (
    <Form.Group>
      <Form.Label>{label}</Form.Label>
      <Form.Control as="select" value={selectedIndex} onChange={(event) => onChange(options.get(parseInt(event.target.value)) as T)}>
        {options.map((option, index) => (
          <option key={index} value={index}>
            {display(option)}
          </option>
        ))}
      </Form.Control>
    </Form.Group>
  );
};

export default SelectField;
