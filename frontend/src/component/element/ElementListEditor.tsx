import { List } from "immutable";
import ElementEditor from "./ElementEditor";
import Element, { ElementType } from "../../model/Element";
import ListEditor from "../../common/ListEditor";
import StateElement from "../../model/StateElement";
import { prepareEmptyId } from "../../model/Id";
import { STATE_ELEMENT_VALUE_TYPES } from "../../service/Types";
import { prepareDefinitionRef } from "../../model/DefinitionRef";

interface ElementListEditorProps {
  elements: List<Element>;
  onElementsChange: (elements: List<Element>) => void;
}

const ElementListEditor = ({ elements, onElementsChange }: ElementListEditorProps) => {
  const prepareEmptyElement = (): StateElement => {
    return {
      elementType: ElementType.State,
      name: "",
      annotations: List(),
      defaultValue: null,
      description: null,
      id: prepareEmptyId(),
      type: prepareDefinitionRef(STATE_ELEMENT_VALUE_TYPES[0]),
    };
  };

  return (
    <ListEditor
      items={elements}
      onItemsChange={onElementsChange}
      header={(element) => element.id.id}
      content={(element, onElementChange) => <ElementEditor element={element} onElementChange={onElementChange} />}
      emptyItem={prepareEmptyElement}
    />
  );
};

export default ElementListEditor;
