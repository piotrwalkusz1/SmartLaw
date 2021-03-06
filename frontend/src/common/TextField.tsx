import { Form } from "react-bootstrap";
import React, { useEffect, useState } from "react";
import { useDebounce } from "use-debounce";

interface TextFieldProps {
  label?: string;
  placeholder?: string;
  value: string;
  onChange?: (value: string) => void;
  onBlur?: () => void;
  disabled?: boolean;
}

const TextField = ({ label, placeholder, value, onChange, onBlur, disabled }: TextFieldProps) => {
  const [currentValue, setCurrentValue] = useState(value);
  const [debouncedValue] = useDebounce(currentValue, 500);
  useEffect(() => setCurrentValue(value), [value]);
  useEffect(() => onChange && onChange(debouncedValue), [debouncedValue]);

  return (
    <Form.Group>
      {label && <Form.Label>{label}</Form.Label>}
      <Form.Control
        type="text"
        placeholder={placeholder}
        value={currentValue}
        onChange={(event) => setCurrentValue(event.target.value)}
        disabled={disabled}
        onBlur={onBlur}
      />
    </Form.Group>
  );
};

export default TextField;
