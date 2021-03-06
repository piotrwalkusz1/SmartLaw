import DefinitionRef from "../../../model/DefinitionRef";
import IdField from "../../IdField";

interface DefinitionRefEditorProps {
  definitionRef: DefinitionRef;
  onChange: (definitionRef: DefinitionRef) => void;
}

const DefinitionRefEditor = ({ definitionRef, onChange }: DefinitionRefEditorProps) => {
  return (
    <div>
      <IdField
        label="Definition id"
        value={definitionRef.definition}
        onValueChange={(definition) => onChange({ ...definitionRef, definition })}
      />
    </div>
  );
};

export default DefinitionRefEditor;
