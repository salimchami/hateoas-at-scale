import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {OAuthService} from 'angular-oauth2-oidc';
import {BehaviorSubject, combineLatest, Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {ToastService, ToastType} from '../../components/toast';
import {environment} from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class AuthService {
  private readonly isAuthenticatedSubject$ = new BehaviorSubject<boolean>(false);
  public isAuthenticated$ = this.isAuthenticatedSubject$.asObservable();
  private readonly isDoneLoadingSubject$ = new BehaviorSubject<boolean>(false);
  public isDoneLoading$ = this.isDoneLoadingSubject$.asObservable();

  /**
   * Publishes `true` if and only if (a) all the asynchronous initial
   * login calls have completed or on error, and (b) the user ended up
   * being authenticated.
   *
   * In essence, it combines:
   *
   * - the latest known state of whether the user is authorized
   * - whether the ajax calls for initial log in have all been done
   */
  public canActivateProtectedRoutes$: Observable<boolean> = combineLatest([
    this.isAuthenticated$,
    this.isDoneLoading$
  ]).pipe(map(values => values.every(b => b)));

  constructor(
    private readonly oauthService: OAuthService,
    private readonly toastService: ToastService,
    private readonly router: Router,
  ) {
    this.oauthService.events
      .subscribe(() => {
        this.isAuthenticatedSubject$.next(this.oauthService.hasValidAccessToken());
      });

    this.isAuthenticatedSubject$.next(this.oauthService.hasValidAccessToken());

    this.oauthService.events
      .pipe(filter(e => ['token_received'].includes(e.type)))
      .subscribe(() => this.oauthService.loadUserProfile());

    this.oauthService.events
      .pipe(filter(e => ['session_terminated', 'session_error'].includes(e.type)))
      .subscribe(() => this.logout());
  }

  public async runInitialLoginSequence(): Promise<void> {
    return this.oauthService.loadDiscoveryDocument()
      .then(() => this.oauthService.tryLogin())
      .then(() => {
        if (this.oauthService.hasValidAccessToken()) {
          return Promise.resolve();
        } else {
          return Promise.reject(new Error());
        }
      })

      .then(() => {
        this.isDoneLoadingSubject$.next(true);
        if (this.oauthService.state && this.oauthService.state !== 'undefined' && this.oauthService.state !== 'null') {
          let stateUrl = this.oauthService.state;
          if (!stateUrl.startsWith('/')) {
            stateUrl = decodeURIComponent(stateUrl);
          }
          this.router.navigateByUrl(stateUrl);
        }
      })
      .catch(() => this.isDoneLoadingSubject$.next(true));
  }

  public logout(redirectUri?: string) {
    this.oauthService.logOut();
    if (redirectUri) {
      this.router.navigateByUrl(redirectUri).then();
    }
  }

  public login(targetUrl?: string) {
    this.oauthService.initLoginFlow(targetUrl ?? this.router.url);
  }

  public register(redirectUrl?: string) {
    if (redirectUrl) {
      this.redirectToRegister(redirectUrl);
    } else {
      this.redirectToRegister(window.location.origin);
    }
  }

  private redirectToRegister(redirectUrl: string) {
    window.location.href = `${environment.keycloakRegistrationUrl}${encodeURIComponent(redirectUrl)}`;
  }

  getAccessToken() {
    return this.oauthService.getAccessToken();
  }

  isValidSession(): boolean {
    const hasValidAccessToken = this.oauthService.hasValidAccessToken();
    const hasValidIdToken = this.oauthService.hasValidIdToken();
    const hasValidToken = hasValidAccessToken && hasValidIdToken;
    const accessTokenExpiration = this.oauthService.getAccessTokenExpiration();
    const idTokenExpiration = this.oauthService.getIdTokenExpiration();
    const now = new Date().getTime();
    const tokenHasExpired = accessTokenExpiration < now || idTokenExpiration < now;
    if (tokenHasExpired && hasValidToken) {
      this.logout();
      this.toastService.showToast(ToastType.WARNING, "Session Expired", "Your session has expired. Please login again.");
    }
    return hasValidToken && !tokenHasExpired;
  }
}
