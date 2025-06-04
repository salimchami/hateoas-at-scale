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
    return this.http.get<any>(endpoint, {headers: headers, withCredentials});
  }

  protected patch(endpoint: string,
                 payload: any, headers?: HttpHeaders, withCredentials: boolean = true
  ): Observable<any> {
    headers?.append('Content-Type', 'application/json');
    return this.http.patch(endpoint, payload, {headers, withCredentials});
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
