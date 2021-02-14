import { decodeList } from "../utils/Decoders";
import Id, { decodeId } from "./Id";
import { List } from "immutable";
import MetaValue, { decodeMetaValue } from "./MetaValue";

export const decodeAnnotation = (json: any): Annotation => {
  return new Annotation(decodeId(json.annotationType), decodeList(json.arguments, decodeMetaValue));
};

export default class Annotation {
  annotationType: Id;
  arguments: List<MetaValue>;

  constructor(annotationType: Id, annotationArguments: List<MetaValue>) {
    this.annotationType = annotationType;
    this.arguments = annotationArguments;
  }
}
