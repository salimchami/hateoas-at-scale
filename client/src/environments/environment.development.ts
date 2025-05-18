import {AuthConfig, OAuthModuleConfig} from 'angular-oauth2-oidc';

const appApiHost = 'http://172.25.0.10:8000';
const keycloakHost = 'http://localhost:8010';
const keycloakRealm = 'hateoasatscale';
const keycloakClientId = 'hateoasatscale-front';
const keycloakRegistrationUrl = `${keycloakHost}/realms/${keycloakRealm}
/protocol/openid-connect/registrations?client_id=${keycloakClientId}&scope=openid%20profile
&response_type=code
&redirect_uri=`
const authConfig: AuthConfig = {
  issuer: `${keycloakHost}/realms/${keycloakRealm}`,
  clientId: keycloakClientId,
  responseType: 'code',
  redirectUri: window.location.origin,
  scope: 'openid profile email',
  useSilentRefresh: true,
  silentRefreshTimeout: 20000,
  timeoutFactor: 0.25,
  sessionChecksEnabled: true,
  showDebugInformation: true,
  clearHashAfterLogin: false,
  nonceStateSeparator: 'semicolon',
  strictDiscoveryDocumentValidation: false,
};

const authOAuthModuleConfig: OAuthModuleConfig = {
  resourceServer: {
    allowedUrls: [`${appApiHost}/v1/api`],
    sendAccessToken: true
  }
}
export const environment: any = {
  production: false,
  appApiHost,
  startupEndpoint: '/users-service/api/v1/users/user-info',
  keycloakRegistrationUrl,
  authConfig,
  authOAuthModuleConfig
};
