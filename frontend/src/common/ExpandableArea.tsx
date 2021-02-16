import { Accordion, Card } from "react-bootstrap";
import React from "react";

interface ExpandableAreaProps {
  header: string;
  children: JSX.Element;
}

const ExpandableArea = ({ header, children }: ExpandableAreaProps) => {
  return (
    <Accordion>
      <Card>
        <Accordion.Toggle as={Card.Header} eventKey="0">
          {header}
        </Accordion.Toggle>
        <Accordion.Collapse eventKey="0">
          <Card.Body>{children}</Card.Body>
        </Accordion.Collapse>
      </Card>
    </Accordion>
  );
};

export default ExpandableArea;
