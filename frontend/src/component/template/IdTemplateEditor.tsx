import Template from "../../model/Template";
import Id, { IdTemplate, IdUtils } from "../../model/Id";
import { Col, Form, Row } from "react-bootstrap";
import StringTemplateEditor from "./StringTemplateEditor";

interface IdTemplateEditorProps {
  template: Template<Id>;
  onChange: (template: IdTemplate) => void;
  label: string;
}

const IdTemplateEditor = ({ template, onChange, label }: IdTemplateEditorProps) => {
  if (IdUtils.isTemplate(template)) {
    return (
      <div>
        <Form.Label>{label}</Form.Label>
        <Row>
          <Col>
            <StringTemplateEditor placeholder="Id" template={template.id} onChange={(id) => onChange({ ...template, id })} />
          </Col>
          <Col>
            <StringTemplateEditor
              placeholder="Namespace"
              template={template.namespace}
              onChange={(namespace) => onChange({ ...template, namespace })}
            />
          </Col>
        </Row>
      </div>
    );
  } else {
    return <div>Template type {template.templateType} is not supported</div>;
  }
};

export default IdTemplateEditor;
