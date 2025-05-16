package com.hateoasatscale.cart.infrastructure.config

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository


// @Configuration
class OauthFeignConfig {
    companion object {
        private const val CLIENT_REGISTRATION_ID = "hateoasatscale"
    }

    private val oAuth2AuthorizedClientService: OAuth2AuthorizedClientService
    private val clientRegistrationRepository: ClientRegistrationRepository

    constructor(
        oAuth2AuthorizedClientService: OAuth2AuthorizedClientService,
        clientRegistrationRepository: ClientRegistrationRepository
    ) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService
        this.clientRegistrationRepository = clientRegistrationRepository
    }

    //@Bean
    //fun requestInterceptor(): RequestInterceptor {
    //    val clientRegistration = clientRegistrationRepository.findByRegistrationId(CLIENT_REGISTRATION_ID)
    //    val clientCredentialsFeignManager = OAuthClientCredentialsFeignManager(authorizedClientManager(), clientRegistration)
    //    return RequestInterceptor { requestTemplate ->
    //        requestTemplate.header("Authorization", "Bearer " + clientCredentialsFeignManager.getAccessToken())
    //    }
//
    //}
}
