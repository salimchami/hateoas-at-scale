package com.hateoasatscale.users.infrastructure.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

class ForwardedHeadersRequestInterceptor : RequestInterceptor {

    override fun apply(template: RequestTemplate) {
        val requestAttributes = RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes
        val request = requestAttributes?.request

        if (request != null) {
            // Propager les en-têtes forwarded de la requête originale
            listOf(
                "X-Forwarded-Host",
                "X-Forwarded-Port",
                "X-Forwarded-Proto",
                "X-Forwarded-For",
                "X-Forwarded-Prefix",
            ).forEach { headerName ->
                val headerValue = request.getHeader(headerName)
                if (headerValue != null) {
                    template.header(headerName, headerValue)
                }
            }
        }
        val auth = SecurityContextHolder.getContext().authentication
        if (auth is JwtAuthenticationToken && !template.headers().containsKey(AUTHORIZATION)) {
            template.header(AUTHORIZATION, "Bearer %s".format(auth.token.tokenValue))
        }
    }
}
