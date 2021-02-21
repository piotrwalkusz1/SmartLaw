import { List } from "immutable";
import ElementEditor from "./ElementEditor";
import Element from "../../model/Element";
import ListEditor from "../../common/ListEditor";
import { prepareEmptyStateElement } from "../../model/StateElement";

interface ElementListEditorProps {
  elements: List<Element>;
  onElementsChange: (elements: List<Element>) => void;
}

const ElementListEditor = ({ elements, onElementsChange }: ElementListEditorProps) => {
  return (
    <ListEditor
      items={elements}
      onItemsChange={onElementsChange}
      header={(element) => element.id.id}
      content={(element, onElementChange) => <ElementEditor element={element} onElementChange={onElementChange} />}
      emptyItem={prepareEmptyStateElement}
      allowRemove={true}
    />
  );
};

export default ElementListEditor;
