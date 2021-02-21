import { Accordion, Card } from "react-bootstrap";
import React from "react";

interface ExpandableAreaProps {
  header: string;
  children: JSX.Element;
  errorBorder?: boolean;
}

const ExpandableArea = ({ header, children, errorBorder }: ExpandableAreaProps) => {
  return (
    <div style={errorBorder ? { border: "solid 2px red" } : {}}>
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
    </div>
  );
};

export default ExpandableArea;
