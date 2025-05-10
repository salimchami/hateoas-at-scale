package com.hateoasatscale.cart.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
  @Bean
  protected fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy? {
    return NullAuthenticatedSessionStrategy()
  }

  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity,
                  @Value("\${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") jwkUri: String): SecurityFilterChain {
    return http.csrf(Customizer.withDefaults())
      .cors(Customizer.withDefaults())
      .authorizeHttpRequests { auth: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
        auth.anyRequest().authenticated()
      }
      .sessionManagement { c ->
        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      }
      .oauth2ResourceServer { obj: OAuth2ResourceServerConfigurer<HttpSecurity?> ->
        obj.jwt { jwtConfigurer ->
          jwtConfigurer.jwkSetUri(jwkUri)
          jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter())
        }
      }
      .exceptionHandling { exception ->
        exception.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      }
      .build()
  }

  @Bean
  fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
    val converter = JwtGrantedAuthoritiesConverter()
    converter.setAuthoritiesClaimName("roles")
    converter.setAuthorityPrefix("ROLE_")

    val jwtConverter = JwtAuthenticationConverter()
    jwtConverter.setJwtGrantedAuthoritiesConverter(converter)
    return jwtConverter
  }
}
