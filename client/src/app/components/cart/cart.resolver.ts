import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Cart} from './cart';
import {CartService} from './cart.service';

@Injectable({providedIn: 'root'})
export class CartResolver implements Resolve<Cart> {
  constructor(private readonly cartService: CartService) {
  }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Cart> | Promise<Cart> | Cart {
    return this.cartService.find();
  }
}
