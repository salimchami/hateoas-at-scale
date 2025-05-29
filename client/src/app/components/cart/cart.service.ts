import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, Observable, of} from 'rxjs';
import {Cart} from './cart';
import {User} from '../user/user';

@Injectable({providedIn: 'root'})
export class CartService extends HttpService {
  constructor(private readonly httpClient: HttpClient) {
    super(httpClient);
  }

  currentUser: User | null = null;

  find(currentUser: User): Observable<Cart> {
    return this.get(currentUser._links['my-cart'].href).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 404) {
          return of({products: [], totalPrice: 0, user: currentUser} as Cart);
        }
        throw error;
      })
    );
  }
}
