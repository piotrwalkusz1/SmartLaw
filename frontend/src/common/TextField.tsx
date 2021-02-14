import { Form } from "react-bootstrap";
import React from "react";

interface TextFieldProps {
  label: string;
  value: string;
  onChange: (value: string) => void;
}

const TextField = ({ label, value, onChange }: TextFieldProps) => {
  return (
    <Form.Group>
      <Form.Label>{label}</Form.Label>
      <Form.Control type="text" value={value} onChange={(event) => onChange(event.target.value)} />
    </Form.Group>
  );
};

export default TextField;
