import {Injectable} from '@angular/core';
import {HttpService} from '../../shared/http.service';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {LocalStorageService} from '../../shared/local-storage.service';
import {catchError, Observable, of} from 'rxjs';
import {Cart} from './cart';

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
}
