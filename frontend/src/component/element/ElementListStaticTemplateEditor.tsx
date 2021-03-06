import StaticTemplate from "../../model/StaticTemplate";
import ElementListEditor from "./ElementListEditor";
import Element, { decodeElement } from "../../model/Element";
import { List } from "immutable";
import { decodeList } from "../../utils/Decoders";

interface ElementListStaticTemplateEditorProps {
  template: StaticTemplate<List<Element>>;
  onTemplateChange: (template: StaticTemplate<List<Element>>) => void;
}

const ElementListStaticTemplateEditor = ({ template, onTemplateChange }: ElementListStaticTemplateEditorProps) => {
  let elements = template.value instanceof List ? (template.value as List<Element>) : decodeList(template.value, decodeElement);

  return <ElementListEditor elements={elements} onElementsChange={(elements) => onTemplateChange({ ...template, value: elements })} />;
};

export default ElementListStaticTemplateEditor;
