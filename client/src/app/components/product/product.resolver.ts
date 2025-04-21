import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Product} from '../../shared/product';
import {ProductService} from './product.service';
import {Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class ProductResolver  implements Resolve<Product> {
  constructor(private readonly productService: ProductService) {
  }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Product> | Promise<Product> | Product {
    return this.productService.find();
  }
}
