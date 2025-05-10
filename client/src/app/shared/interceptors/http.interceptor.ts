import {HttpErrorResponse, HttpEvent, HttpEventType, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {inject} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from '../authentication';
import {LoadingService} from '../loading/loading.service';

export function httpInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const loadingService = inject(LoadingService);
  const authService = inject(AuthService);
  const router = inject(Router);
  const token = authService.getAccessToken();
  const authReq = req.withCredentials ? req.clone({headers: req.headers.set('Authorization', `Bearer ${token}`)}) : req;

  return next(authReq).pipe(
    tap(event => {
      if (event.type === HttpEventType.Sent) {
        loadingService.show();
      }
      if (event.type === HttpEventType.Response) {
        loadingService.hide();
      }
    }),
    catchError((error: HttpErrorResponse) => {
      loadingService.hide();
      if (error.status === 403) {
        router.navigate(['/404']).then();
      }
      if(error.status === 401) {
        router.navigate(['/']).then();
      }
      if (error.status >= 500) {
        router.navigate(['/500']).then();
      }
      return throwError(() => error);
    })
  );
}
