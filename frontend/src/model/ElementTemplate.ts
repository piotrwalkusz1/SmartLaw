import Template from "./Template";
import Id from "./Id";
import { List } from "immutable";
import Annotation from "./Annotation";
import Element from "./Element";

export default interface ElementTemplate extends Template<Element> {
  id: Template<Id>;
  annotations: Template<List<Annotation>>;
}
