import SelectField from "../../../common/SelectField";
import Type, { TypeKind } from "../../../model/Type";
import { List } from "immutable";
import DefinitionRefEditor from "./DefinitionRefEditor";
import { GenericTypeUtils } from "../../../model/GenericType";
import { InterfaceRefUtils } from "../../../model/InterfaceRef";
import DefinitionRef, { DefinitionRefUtils } from "../../../model/DefinitionRef";

interface TypeEditorProps {
  type: Type;
  onChange: (type: Type) => void;
}

const TypeEditor = ({ type, onChange }: TypeEditorProps) => {
  const prepareEmptyType = (typeKind: TypeKind): Type => {
    switch (typeKind) {
      case TypeKind.GenericType:
        return GenericTypeUtils.create();
      case TypeKind.InterfaceRef:
        return InterfaceRefUtils.create();
      case TypeKind.DefinitionRef:
        return DefinitionRefUtils.create();
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
