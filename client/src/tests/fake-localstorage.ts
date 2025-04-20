export const localStorageMock = (() => {
  let store: Record<string, string> = {};

  return {
    getItem(key: string): string | null {
      return store[key] || null;
    },
    setItem(key: string, value: string): void {
      store[key] = value;
    },
    removeItem(key: string): void {
      delete store[key];
    },
    clear(): void {
      store = {};
    },
    length: 0,
    key(index: number): string | null {
      return Object.keys(store)[index] || null;
    }
  };
})();
