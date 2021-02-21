import RegexValidator from "../../model/RegexValidator";
import TextField from "../../common/TextField";

interface RegexValidatorEditorProps {
  validator: RegexValidator;
  onValidatorChange: (validator: RegexValidator) => void;
}

const RegexValidatorEditor = ({ validator, onValidatorChange }: RegexValidatorEditorProps) => {
  return <TextField placeholder="Regex" value={validator.regex} onChange={(regex) => onValidatorChange({ ...validator, regex })} />;
};

export default RegexValidatorEditor;
