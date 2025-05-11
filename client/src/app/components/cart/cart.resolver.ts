import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable, switchMap} from 'rxjs';
import {Cart} from './cart';
import {CartService} from './cart.service';
import {UserService} from '../user/user-service';

@Injectable({providedIn: 'root'})
export class CartResolver implements Resolve<Cart> {
  constructor(
    private readonly cartService: CartService,
    private readonly userService: UserService
  ) {
  }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<Cart> | Promise<Cart> | Cart {
    return this.userService.findCurrentUser().pipe(
      switchMap(currentUser => this.cartService.find(currentUser))
    );
  }
}
