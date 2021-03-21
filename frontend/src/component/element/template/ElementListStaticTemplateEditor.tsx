import StaticTemplate from "../../../model/StaticTemplate";
import ElementListEditor from "../ElementListEditor";
import Element, { elementMeta } from "../../../model/Element";
import { List } from "immutable";
import { decodeList } from "../../../utils/Decoders";

interface ElementListStaticTemplateEditorProps {
  template: StaticTemplate<List<Element>>;
  onTemplateChange: (template: StaticTemplate<List<Element>>) => void;
}

const ElementListStaticTemplateEditor = ({ template, onTemplateChange }: ElementListStaticTemplateEditorProps) => {
  let elements =
    template.value instanceof List ? (template.value as List<Element>) : decodeList(template.value, elementMeta.decodeOrException);

  return <ElementListEditor elements={elements} onElementsChange={(elements) => onTemplateChange({ ...template, value: elements })} />;
};

export default ElementListStaticTemplateEditor;
