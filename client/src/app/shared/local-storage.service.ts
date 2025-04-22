import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LocalStorageService {
  constructor() {
  }

  private setItem(key: string, value: any): void {
    try {
      const jsonValue = JSON.stringify(value);
      localStorage.setItem(key, jsonValue);
    } catch (error) {
      console.error('Error saving to local storage', error);
    }
  }

  private getItem<T>(key: string): T | null {
    try {
      const value = localStorage.getItem(key);
      return value ? JSON.parse(value) : null;
    } catch (error) {
      console.error('Error reading from local storage', error);
      return null;
    }
  }

  private removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  clear(): void {
    localStorage.clear();
  }

  getSelectedProductLink(): string | null {
    return this.getItem<string>('selectedProductLink');
  }

  setSelectedProductLink(href: string): void {
    this.setItem('selectedProductLink', href);
  }

  getCurrentUser(): string | null {
    return this.getItem<string>('currentUser');
  }

  removeCurrentUser(): void {
    this.removeItem('currentUser');
  }

  setCurrentUser(user: string): void {
    this.setItem('currentUser', user);
  }

  setSelectedUsername(username: string): void {
    this.setItem('selectedUsername', username);
  }

  getSelectedUsername(): string | null {
    return this.getItem<string>('selectedUsername');
  }

  removeSelectedUsername(): void {
    this.removeItem('selectedUsername');
  }

  addToCart(cartProducts: string) {
    this.setItem('cartProducts', cartProducts);
  }

  getCartProducts() {
    return this.getItem<string>('cartProducts');
  }
}
