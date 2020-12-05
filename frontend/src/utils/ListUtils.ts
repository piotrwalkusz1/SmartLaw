import { List } from "immutable";

export const moveItemInList = <T extends {}>(list: List<T>, sourceIndex: number, destinationIndex: number): List<T> => {
  const itemToMove = list.get(sourceIndex) as T;

  return list.delete(sourceIndex).insert(destinationIndex, itemToMove);
};
