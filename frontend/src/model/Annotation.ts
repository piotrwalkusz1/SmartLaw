import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import MetaValue, { metaValueMeta } from "./MetaValue";
import { WrapWithTemplate } from "./WrapWithTemplate";
import { buildModelUtilsWithTemplate } from "../utils/ModelUtils";
import { listMeta } from "../utils/Reflection";
import { TemplateType } from "./TemplateType";

export default interface Annotation {
  annotationType: Id;
  arguments: List<MetaValue>;
}

export interface AnnotationTemplate extends WrapWithTemplate<Annotation> {}

export const AnnotationUtils = buildModelUtilsWithTemplate<Annotation, AnnotationTemplate>(TemplateType.AnnotationTemplate, {
  annotationType: IdUtils.metaData,
  arguments: listMeta(metaValueMeta),
});
