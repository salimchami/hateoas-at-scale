package com.hateoasatscale.users.infrastructure.config.specific

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Configuration(proxyBeanMethods = false)
class ProductsServiceFeignConfig {

     @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template: RequestTemplate ->
            val requestAttributes = RequestContextHolder.getRequestAttributes()
            if (requestAttributes is ServletRequestAttributes) {
                val httpServletRequest = requestAttributes.request

                // Propager les en-têtes X-Forwarded-* reçus
                val forwardedHost = httpServletRequest.getHeader("X-Forwarded-Host")
                val forwardedPort = httpServletRequest.getHeader("X-Forwarded-Port")
                val forwardedProto = httpServletRequest.getHeader("X-Forwarded-Proto")
                val forwardedPrefix = httpServletRequest.getHeader("X-Forwarded-Prefix")
                val forwardedFor = httpServletRequest.getHeader("X-Forwarded-For")
                val hostHeader = httpServletRequest.getHeader("Host") // Récupérer l'en-tête Host original

                // Ajouter les en-têtes à la requête Feign sortante
                if (!forwardedHost.isNullOrEmpty()) {
                    template.header("X-Forwarded-Host", forwardedHost)
                }
                if (!forwardedPort.isNullOrEmpty()) {
                    template.header("X-Forwarded-Port", forwardedPort)
                }
                if (!forwardedProto.isNullOrEmpty()) {
                    template.header("X-Forwarded-Proto", forwardedProto)
                }
                if (!forwardedPrefix.isNullOrEmpty()) {
                    template.header("X-Forwarded-Prefix", forwardedPrefix)
                }
                if (!forwardedFor.isNullOrEmpty()) {
                    template.header("X-Forwarded-For", forwardedFor)
                }
                // Le Host header doit aussi être propagé car il peut être utilisé par HATEOAS
                if (!hostHeader.isNullOrEmpty()) {
                    template.header("Host", hostHeader)
                }
            }

            // Pour l'autorisation, si nécessaire, générez le token ici
            template.header("Authorization", generateToken())
        }
    }


    private fun generateToken(): String? {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth is JwtAuthenticationToken) {
            return "Bearer %s".format(auth.token.tokenValue)
        }
        return null
    }
}
