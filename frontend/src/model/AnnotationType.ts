import { decodeList, decodeNullable, decodeString } from "../utils/Decoders";
import Id, { IdUtils } from "./Id";
import { List } from "immutable";
import MetaArgument, { decodeMetaArgument } from "./MetaArgument";
import Annotation, { AnnotationUtils } from "./Annotation";

export const decodeAnnotationType = (json: any): AnnotationType => {
  return new AnnotationType(
    IdUtils.decode(json.id),
    decodeList(json.annotations, AnnotationUtils.decode),
    decodeNullable(json.name, decodeString),
    decodeNullable(json.description, decodeString),
    decodeList(json.arguments, decodeMetaArgument)
  );
};

export default class AnnotationType {
  id: Id;
  annotations: List<Annotation>;
  name: string | null;
  description: string | null;
  arguments: List<MetaArgument>;

  constructor(id: Id, annotations: List<Annotation>, name: string | null, description: string | null, metaArguments: List<MetaArgument>) {
    this.id = id;
    this.annotations = annotations;
    this.name = name;
    this.description = description;
    this.arguments = metaArguments;
  }
}
