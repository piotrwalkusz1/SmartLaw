import SelectField from "../../../common/SelectField";
import Type, { TypeKind } from "../../../model/Type";
import { prepareGenericType } from "../../../model/GenericType";
import { prepareInterfaceRef } from "../../../model/InterfaceRef";
import { prepareEmptyId } from "../../../model/Id";
import DefinitionRef, { prepareDefinitionRef } from "../../../model/DefinitionRef";
import { List } from "immutable";
import DefinitionRefEditor from "./DefinitionRefEditor";

interface TypeEditorProps {
  type: Type;
  onChange: (type: Type) => void;
}

const TypeEditor = ({ type, onChange }: TypeEditorProps) => {
  const prepareEmptyType = (typeKind: TypeKind): Type => {
    switch (typeKind) {
      case TypeKind.GenericType:
        return prepareGenericType("");
      case TypeKind.InterfaceRef:
        return prepareInterfaceRef(prepareEmptyId());
      case TypeKind.DefinitionRef:
        return prepareDefinitionRef(prepareEmptyId());
      default:
        throw Error("Type kind " + typeKind + " is not supported");
    }
  };

  const renderTypeEditor = () => {
    switch (type.type) {
      case TypeKind.DefinitionRef:
        return <DefinitionRefEditor definitionRef={type as DefinitionRef} onChange={(definitionRef) => onChange(definitionRef)} />;
      default:
        return <div>Type kind {type.type} is not supported</div>;
    }
  };

  return (
    <div>
      <SelectField
        label="Type kind"
        value={type.type}
        onChange={(typeKind) => onChange(prepareEmptyType(typeKind))}
        options={List([TypeKind.DefinitionRef])}
        display={(typeKind) => typeKind}
      />
      {renderTypeEditor()}
    </div>
  );
};

export default TypeEditor;
