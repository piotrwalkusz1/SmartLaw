import { DependencyList, useEffect } from "react";

export const useFetchedData = <T>(fetch: () => Promise<T>, action: (data: T) => void, deps?: DependencyList) => {
  useEffect(() => {
    let isSubscribed = true;
    fetch().then((data) => {
      if (isSubscribed) {
        action(data);
      }
    });

    return () => {
      isSubscribed = false;
    };
  }, deps);
};
