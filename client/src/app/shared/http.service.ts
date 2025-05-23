import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

class HttpParam {
  constructor(
    public key: string,
    public value: string,
  ) {
  }
}

@Injectable()
export class HttpService {

  baseNotifConfig: any;

  constructor(protected http: HttpClient) {
    this.baseNotifConfig = {
      horizontalPosition: 'center',
      verticalPosition: 'top',
    };
  }

  protected get(endpoint: string, headers?: HttpHeaders,
                requestParams?: HttpParam[], withCredentials: boolean = true
  ): Observable<any> {
    endpoint = this.fillParams(endpoint, requestParams);
    headers?.append('Content-Type', 'application/json')
    return this.http.get<any>(endpoint, {headers: headers, withCredentials });
  }

  protected post(endpoint: string,
                 payload: any
  ): Observable<any> {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    return this.http.post(endpoint, payload, {headers});
  }

  protected put(endpoint: string, headers?: HttpHeaders,
                payload?: any
  ): Observable<any> {
    headers?.append('Content-Type', 'application/json');
    return this.http.put(endpoint, payload ?? null, {headers});
  }

  protected url(endpoint: string): string {
    return `${environment.appApiHost}${endpoint}`;
  }

  private fillParams(endpoint: string, requestParams: HttpParam[] = []) {
    requestParams.forEach(param => {
      endpoint = endpoint?.replace('{' + param.key + '}', param.value);
    });
    return endpoint;
  }
}
