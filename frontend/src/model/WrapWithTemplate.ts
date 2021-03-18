import Template from "./Template";

export type WrapWithTemplate<Type> = {
  [Property in keyof Type as Exclude<Property, "elementType">]: Template<Type[Property]>;
} &
  Template<Type>;
