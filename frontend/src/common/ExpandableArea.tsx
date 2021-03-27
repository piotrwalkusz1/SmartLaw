import { Accordion, Card } from "react-bootstrap";
import { ReactElement } from "react";

interface ExpandableAreaProps {
  header?: string | ReactElement;
  children: JSX.Element;
  errorBorder?: boolean;
}

const ExpandableArea = ({ header, children, errorBorder }: ExpandableAreaProps) => {
  const renderBody = () => {
    if (header) {
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
    } else {
      return children;
    }
  };

  return <div style={errorBorder ? { border: "solid 2px red" } : {}}>{renderBody()}</div>;
};

export default ExpandableArea;
