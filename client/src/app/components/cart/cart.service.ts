import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {LocalStorageService} from '../../shared/local-storage.service';
import {catchError, Observable, of} from 'rxjs';
import {Cart} from './cart';
import {LocalCartProducts} from '../../shared/local-cart-products';

@Injectable({providedIn: 'root'})
export class CartService extends HttpService {
  constructor(private readonly httpClient: HttpClient,
              private readonly localStorageService: LocalStorageService,) {
    super(httpClient);
  }

  find(): Observable<Cart> {
    const user = this.localStorageService.getCurrentUser();
    if (user) {
      const currentUser = JSON.parse(user);
      return this.get(currentUser._links['cart'].href).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 404) {
            return of({products: [], totalPrice: 0, user: currentUser} as Cart);
          }
          throw error; // Re-throw other errors
        })
      );
    }
    throw new Error('No product selected...');

  }


updateCartFrom(cart: Cart): Cart {
  const localCartProductsStr = this.localStorageService.getCartProducts();
  if (!localCartProductsStr) {
    return cart;
  }

  const localCartProducts = LocalCartProducts.from(JSON.parse(localCartProductsStr));

  const mergedProducts = [...cart.products];
  const productMap = new Map(cart.products.map(p => [p.name, p]));

  localCartProducts.value.forEach(localProduct => {
    const name = localProduct.product.name;

    if (productMap.has(name)) {
      const index = mergedProducts.findIndex(p => p.name === name);
      const existing = productMap.get(name)!;
      const unitPrice = existing.totalPrice / existing.quantity;

      mergedProducts[index] = {
        ...existing,
        quantity: existing.quantity + localProduct.quantity,
        totalPrice: unitPrice * (existing.quantity + localProduct.quantity)
      };
    } else {
      mergedProducts.push({
        name: name,
        quantity: localProduct.quantity,
        totalPrice: localProduct.product.price * localProduct.quantity,
        _links: []
      });
    }
  });

  const totalPrice = mergedProducts.reduce((sum, p) => sum + p.totalPrice, 0);

  return new Cart(totalPrice, cart.user, mergedProducts);
}
}
