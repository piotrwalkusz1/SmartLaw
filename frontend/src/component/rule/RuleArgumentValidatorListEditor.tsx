import { List } from "immutable";
import Validator, { ValidatorType } from "../../model/Validator";
import ListEditor from "../../common/ListEditor";
import React from "react";
import ExpandableArea from "../../common/ExpandableArea";
import RegexValidator from "../../model/RegexValidator";
import ValidatorEditor from "../validator/ValidatorEditor";

interface RuleArgumentValidatorListEditorProps {
  validators: List<Validator>;
  onValidatorsChange: (validators: List<Validator>) => void;
}

const RuleArgumentValidatorListEditor = ({ validators, onValidatorsChange }: RuleArgumentValidatorListEditorProps) => {
  const prepareEmptyValidator = (): RegexValidator => {
    return {
      type: ValidatorType.Regex,
      regex: "",
    };
  };

  return (
    <ExpandableArea header="Validators">
      <ListEditor
        items={validators}
        onItemsChange={onValidatorsChange}
        header={(_, index) => "Validator " + (index + 1)}
        content={(validator, onValidatorChange) => <ValidatorEditor validator={validator} onValidatorChange={onValidatorChange} />}
        emptyItem={prepareEmptyValidator}
        allowRemove={true}
      />
    </ExpandableArea>
  );
};

export default RuleArgumentValidatorListEditor;
