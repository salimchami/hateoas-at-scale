import {CanActivateFn} from "@angular/router";
import {inject} from "@angular/core";
import {Observable} from "rxjs";
import {AuthService} from './auth.service';

export const AuthGuard: CanActivateFn = (): Observable<boolean> => {
  const authenticationService = inject(AuthService);
  return authenticationService.isAuthenticated$;
};
