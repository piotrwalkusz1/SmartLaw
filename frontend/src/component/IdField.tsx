import TextField from "../common/TextField";
import Id from "../model/Id";
import { Col, Form, Row } from "react-bootstrap";

export interface IdFieldProps {
  label: string;
  value: Id;
  onValueChange: (value: Id) => void;
}

const IdField = ({ label, value, onValueChange }: IdFieldProps) => {
  return (
    <div>
      <Form.Label>{label}</Form.Label>
      <Row>
        <Col>
          <TextField placeholder="Id" value={value.id} onChange={(id) => onValueChange({ ...value, id })} />
        </Col>
        <Col>
          <TextField
            placeholder="Namespace"
            value={value.namespace}
            onChange={(namespace) =>
              onValueChange({
                ...value,
                namespace,
              })
            }
          />
        </Col>
      </Row>
    </div>
  );
};

export default IdField;
