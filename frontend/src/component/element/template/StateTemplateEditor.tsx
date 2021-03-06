import StateTemplate from "../../../model/StateTemplate";
import IdField from "../../IdField";
import Id, { prepareEmptyId } from "../../../model/Id";
import { TemplateType } from "../../../model/Template";

interface StateTemplateEditorProps {
  stateTemplate: StateTemplate;
  onChange: (stateTemplate: StateTemplate) => void;
}

const StateTemplateEditor = ({ stateTemplate, onChange }: StateTemplateEditorProps) => {
  // const getElementId = (): Id => {
  //   if (!(stateTemplate.id.templateType === TemplateType.Static)) {
  //     return prepareEmptyId()
  //   }
  //
  //
  // }
  //
  // return (
  //   <div>
  //     <IdField label="Id" value={stateTemplate.id} onValueChange={(id) => onElementChange({ ...element, id })} />
  //   </div>
  // )
};

export default StateTemplateEditor;
