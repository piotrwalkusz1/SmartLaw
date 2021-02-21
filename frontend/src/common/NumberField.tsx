import { Form } from "react-bootstrap";
import React from "react";

interface NumberFieldProps {
  label?: string;
  placeholder?: string;
  value: number;
  onChange: (value: number) => void;
}

const NumberField = ({ label, placeholder, value, onChange }: NumberFieldProps) => {
  return (
    <Form.Group>
      {label && <Form.Label>{label}</Form.Label>}
      <Form.Control type="number" placeholder={placeholder} value={value} onChange={(event) => onChange(Number(event.target.value))} />
    </Form.Group>
  );
};

export default NumberField;
