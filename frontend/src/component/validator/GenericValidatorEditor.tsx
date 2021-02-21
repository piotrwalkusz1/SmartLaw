import TextField from "../../common/TextField";
import GenericValidator from "../../model/GenericValidator";

interface GenericValidatorEditorProps {
  validator: GenericValidator;
  onValidatorChange: (validator: GenericValidator) => void;
}

const GenericValidatorEditor = ({ validator, onValidatorChange }: GenericValidatorEditorProps) => {
  return (
    <TextField
      placeholder="Expression"
      value={validator.expression}
      onChange={(expression) => onValidatorChange({ ...validator, expression })}
    />
  );
};

export default GenericValidatorEditor;
