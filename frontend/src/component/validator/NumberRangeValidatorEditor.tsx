import NumberField from "../../common/NumberField";
import NumberRangeValidator from "../../model/NumberRangeValidator";

interface NumberRangeValidatorEditorProps {
  validator: NumberRangeValidator;
  onValidatorChange: (validator: NumberRangeValidator) => void;
}

const NumberRangeValidatorEditor = ({ validator, onValidatorChange }: NumberRangeValidatorEditorProps) => {
  return (
    <div style={{ display: "flex", alignItems: "baseline" }}>
      <div style={{ marginRight: "15px" }}>From</div>
      <NumberField value={validator.minValue} onChange={(minValue) => onValidatorChange({ ...validator, minValue })} />
      <div style={{ marginLeft: "15px", marginRight: "15px" }}>to</div>
      <NumberField value={validator.maxValue} onChange={(maxValue) => onValidatorChange({ ...validator, maxValue })} />
    </div>
  );
};

export default NumberRangeValidatorEditor;
