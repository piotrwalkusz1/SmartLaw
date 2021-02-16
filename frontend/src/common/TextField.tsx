import { Form } from "react-bootstrap";
import React from "react";

interface TextFieldProps {
  label?: string;
  placeholder?: string;
  value: string;
  onChange: (value: string) => void;
}

const TextField = ({ label, placeholder, value, onChange }: TextFieldProps) => {
  return (
    <Form.Group>
      {label && <Form.Label>{label}</Form.Label>}
      <Form.Control type="text" placeholder={placeholder} value={value} onChange={(event) => onChange(event.target.value)} />
    </Form.Group>
  );
};

export default TextField;
