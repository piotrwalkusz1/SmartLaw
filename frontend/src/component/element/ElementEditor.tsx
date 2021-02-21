import Element, { ElementType } from "../../model/Element";
import StateElementEditor from "./StateElementEditor";
import StateElement, { prepareEmptyStateElement } from "../../model/StateElement";
import SelectField from "../../common/SelectField";
import { List } from "immutable";

interface ElementEditorProps {
  element: Element;
  onElementChange: (element: Element) => void;
}

const ElementEditor = ({ element, onElementChange }: ElementEditorProps) => {
  const renderElement = () => {
    switch (element.elementType) {
      case ElementType.State:
        return <StateElementEditor element={element as StateElement} onElementChange={onElementChange} />;
      default:
        return <div>Element {element.elementType} is not supported</div>;
    }
  };

  const prepareEmptyElement = (elementType: ElementType): Element => {
    switch (elementType) {
      case ElementType.State:
        return prepareEmptyStateElement();
      default:
        throw Error("Element type " + elementType + " is not supported");
    }
  };

  return (
    <div>
      <SelectField
        label="Element type"
        value={element.elementType}
        onChange={(elementType) => onElementChange(prepareEmptyElement(elementType))}
        options={List([ElementType.State])}
        display={(elementType) => elementType}
      />
      {renderElement()}
    </div>
  );
};

export default ElementEditor;
