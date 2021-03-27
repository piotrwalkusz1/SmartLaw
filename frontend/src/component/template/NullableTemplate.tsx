import Template from "../../model/Template";
import { isStaticTemplate, prepareStaticTemplate } from "../../model/StaticTemplate";
import TemplateEditor from "./TemplateEditor";
import { createTemplateByMetaData, MetaData } from "../../utils/Reflection";
import ExpandableArea from "../../common/ExpandableArea";
import { Button, Form } from "react-bootstrap";

interface NullableTemplateProps<T> {
  template: Template<T>;
  onTemplateChange: (template: Template<T>) => void;
  metaData: MetaData<T>;
  fieldName?: string;
}

const NullableTemplate = <T,>({ template, onTemplateChange, metaData, fieldName }: NullableTemplateProps<T>) => {
  if (isStaticTemplate(template) && template.value === null) {
    return (
      <Form.Group>
        <Form.Label>{fieldName}</Form.Label>
        <div>
          <Button onClick={() => onTemplateChange(createTemplateByMetaData(metaData) as Template<T>)}>Create value</Button>
        </div>
      </Form.Group>
    );
  } else {
    return (
      <ExpandableArea header={fieldName}>
        <div>
          <Button onClick={() => onTemplateChange(prepareStaticTemplate(null))}>Remove value</Button>
          <TemplateEditor template={template} onTemplateChange={onTemplateChange} metaData={metaData} />
        </div>
      </ExpandableArea>
    );
  }
};

export default NullableTemplate;
