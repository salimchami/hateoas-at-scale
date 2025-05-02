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
    },
    getSelectedProductLink(): string | null {
      return this.getItem('selectedProductLink');
    },

    setSelectedProductLink(href: string): void {
      this.setItem('selectedProductLink', href);
    },

    getCurrentUser(): string | null {
      return this.getItem('currentUser');
    },

    removeCurrentUser(): void {
      this.removeItem('currentUser');
    },

    setCurrentUser(user: string): void {
      this.setItem('currentUser', user);
    },

    setSelectedUsername(username: string): void {
      this.setItem('selectedUsername', username);
    },

    getSelectedUsername(): string | null {
      return this.getItem('selectedUsername');
    },

    removeSelectedUsername(): void {
      this.removeItem('selectedUsername');
    }
  };
})();
