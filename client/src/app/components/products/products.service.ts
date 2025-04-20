import {Injectable} from '@angular/core';
import {Products} from './products';
import {Observable} from 'rxjs';
import {HttpService} from '../../shared/http.service';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class ProductsService extends HttpService {
  constructor(private readonly httpClient: HttpClient) {
    super(httpClient);
  }
  findAll(url: string): Observable<Products> {
    return this.get(url).pipe(map(products => Products.from(products)));
  }
}
