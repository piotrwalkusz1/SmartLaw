import { BaseMetaData, buildBaseMetaData, enumMeta, excludeFromTemplate } from "../utils/Reflection";

export enum MetaValueType {
  Primitive = "Primitive",
  Rule = "Rule",
  Map = "Map",
}

export default interface MetaValue {
  type: MetaValueType;
}

export const metaValueMeta: BaseMetaData<MetaValue, MetaValueType> = buildBaseMetaData<MetaValue, MetaValueType>(
  "type",
  MetaValueType,
  null,
  {
    type: excludeFromTemplate(enumMeta(MetaValueType)),
  }
);
