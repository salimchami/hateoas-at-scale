import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from '../../shared/local-storage.service';
import {Product} from '../../shared/product';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {LocalCartProduct} from '../../shared/local-cart-product';
import {LocalCartProducts} from '../../shared/local-cart-products';

@Injectable({providedIn: 'root'})
export class ProductService extends HttpService {
  constructor(private readonly httpClient: HttpClient,
              private readonly localStorageService: LocalStorageService,) {
    super(httpClient);
  }

  find(): Observable<Product> {
    const localStorageProductLink = this.localStorageService.getSelectedProductLink();
    if (localStorageProductLink) {
      return this.get(localStorageProductLink).pipe(map(product => Product.from(product)));
    }
    throw new Error('No product selected...');
  }

  addToCart(product: Product, quantity: number) {
    const productToAdd = new LocalCartProduct(product, quantity);
    const localCartProducts = this.localStorageService.getCartProducts();
    const cartProducts = localCartProducts ? LocalCartProducts.from(JSON.parse(localCartProducts)) : new LocalCartProducts([]);
    cartProducts.add(productToAdd);
    this.localStorageService.addToCart(JSON.stringify(cartProducts));
  }
}
