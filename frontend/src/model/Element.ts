import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import Annotation, { AnnotationUtils } from "./Annotation";
import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate, listMeta } from "../utils/Reflection";

export enum ElementType {
  State = "State",
  EnumDefinition = "EnumDefinition",
  ActionDefinition = "ActionDefinition",
  Function = "Function",
  ActionValidation = "ActionValidation",
}

export default interface Element {
  elementType: ElementType;
  id: Id;
  annotations: List<Annotation>;
}

export const elementMeta: BaseMetaData<Element, ElementType> = buildBaseMetaData<Element, ElementType>("elementType", ElementType, null, {
  elementType: excludeFromTemplate(enumMeta(ElementType)),
  id: IdUtils.metaData,
  annotations: listMeta(AnnotationUtils.metaData),
});
