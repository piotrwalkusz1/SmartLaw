import Element, { ElementType } from "../../model/Element";
import StateElementEditor from "./StateElementEditor";
import StateElement from "../../model/StateElement";

interface ElementEditorProps {
  element: Element;
  onElementChange: (element: Element) => void;
}

const ElementEditor = ({ element, onElementChange }: ElementEditorProps) => {
  switch (element.elementType) {
    case ElementType.State:
      return <StateElementEditor element={element as StateElement} onElementChange={onElementChange} />;
    default:
      return <div>Element {element.elementType} is not supported</div>;
  }
};

export default ElementEditor;
