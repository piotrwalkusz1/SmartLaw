import { Card } from "react-bootstrap";
import { PlusSquare } from "react-bootstrap-icons";
import React from "react";

interface AddButtonProps {
  onClick: () => void;
}

const AddButton = ({ onClick }: AddButtonProps) => {
  return (
    <Card style={{ marginTop: "30px", marginBottom: "15px" }}>
      <Card.Header>
        <div style={{ textAlign: "center", fontSize: "26px" }}>
          <span style={{ cursor: "pointer" }} onClick={onClick}>
            <PlusSquare />
          </span>
        </div>
      </Card.Header>
    </Card>
  );
};

export default AddButton;
