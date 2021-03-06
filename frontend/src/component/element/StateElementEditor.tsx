import StateElement from "../../model/StateElement";
import IdField from "../IdField";
import TextField from "../../common/TextField";
import TypeEditor from "./type/TypeEditor";

interface StateElementEditorProps {
  element: StateElement;
  onElementChange: (element: StateElement) => void;
}

const StateElementEditor = ({ element, onElementChange }: StateElementEditorProps) => {
  return (
    <div>
      <IdField label="Id" value={element.id} onValueChange={(id) => onElementChange({ ...element, id })} />
      <TextField label="Name" value={element.name} onChange={(name) => onElementChange({ ...element, name })} />
      <TypeEditor type={element.type} onChange={(type) => onElementChange({ ...element, type })} />
    </div>
  );
};

export default StateElementEditor;
