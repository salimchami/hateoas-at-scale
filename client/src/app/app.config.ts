import {ApplicationConfig, inject, provideAppInitializer, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {OAuthService, provideOAuthClient} from 'angular-oauth2-oidc';
import {environment} from '../environments/environment';
import {AuthService} from './shared/authentication';
import {httpInterceptor} from './shared/interceptors/http.interceptor';
import {provideAnimations} from '@angular/platform-browser/animations';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideHttpClient(withInterceptors([httpInterceptor])),
    provideOAuthClient(environment.authOAuthModuleConfig),
    provideAppInitializer(authAppInitializerFactory),
    provideAnimations(),
  ]
};

export function authAppInitializerFactory() {
  const authService = inject(AuthService);
  const oauthService = inject(OAuthService);
  oauthService.configure(environment.authConfig);
  return authService.runInitialLoginSequence();
}
