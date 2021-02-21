import Validator, { ValidatorType } from "../../model/Validator";
import GenericValidatorEditor from "./GenericValidatorEditor";
import GenericValidator, { prepareEmptyGenericValidator } from "../../model/GenericValidator";
import RegexValidator, { prepareEmptyRegexValidator } from "../../model/RegexValidator";
import NumberRangeValidator, { prepareEmptyNumberRangeValidator } from "../../model/NumberRangeValidator";
import RegexValidatorEditor from "./RegexValidatorEditor";
import NumberRangeValidatorEditor from "./NumberRangeValidatorEditor";
import SelectField from "../../common/SelectField";
import { List } from "immutable";

interface ValidatorEditorProps {
  validator: Validator;
  onValidatorChange: (validator: Validator) => void;
}

const ValidatorEditor = ({ validator, onValidatorChange }: ValidatorEditorProps) => {
  const renderValidator = () => {
    switch (validator.type) {
      case ValidatorType.Generic:
        return <GenericValidatorEditor validator={validator as GenericValidator} onValidatorChange={onValidatorChange} />;
      case ValidatorType.Regex:
        return <RegexValidatorEditor validator={validator as RegexValidator} onValidatorChange={onValidatorChange} />;
      case ValidatorType.NumberRange:
        return <NumberRangeValidatorEditor validator={validator as NumberRangeValidator} onValidatorChange={onValidatorChange} />;
      default:
        return <div>Validator type is not supported</div>;
    }
  };

  const prepareEmptyValidator = (validatorType: ValidatorType): Validator => {
    switch (validatorType) {
      case ValidatorType.Generic:
        return prepareEmptyGenericValidator();
      case ValidatorType.Regex:
        return prepareEmptyRegexValidator();
      case ValidatorType.NumberRange:
        return prepareEmptyNumberRangeValidator();
      default:
        throw Error("Validator type " + validatorType + " is not supported");
    }
  };

  return (
    <div>
      <SelectField
        label="Validator type"
        value={validator.type}
        onChange={(validatorType) => onValidatorChange(prepareEmptyValidator(validatorType))}
        options={List([ValidatorType.NumberRange, ValidatorType.Regex, ValidatorType.Generic])}
        display={(validatorType) => validatorType}
      />
      {renderValidator()}
    </div>
  );
};

export default ValidatorEditor;
