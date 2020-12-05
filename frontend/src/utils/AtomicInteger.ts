export class AtomicInteger {
  private value: number = 0;

  next(): number {
    return this.value++;
  }
}
