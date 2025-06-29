import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService} from '../../shared/local-storage.service';
import {Product} from '../../shared/product';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

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
    this.patch(product._links.addProductToCart.href, {name: product.name, quantity}).subscribe(
      () => {
      },
      error => {
        console.error(error);
      }
    );
  }
}
