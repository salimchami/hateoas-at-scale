package com.hateoasatscale.users

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.test.context.support.WithSecurityContextFactory

class WithJwtMockSecurityContextFactory() :
  WithSecurityContextFactory<WithJwtMock> {
  override fun createSecurityContext(annotation: WithJwtMock): SecurityContext {
    val jwt = Jwt.withTokenValue("token")
      .header("alg", "none")
      .claim("preferred_username", annotation.user.sub)
      .claim("given_name", annotation.user.givenName)
      .claim("family_name", annotation.user.familyName)
      .build()
    val authorities = annotation.user.roles.map { role -> SimpleGrantedAuthority(role) }

    val authentication = JwtAuthenticationToken(jwt, authorities)

    val context = SecurityContextHolder.createEmptyContext()
    context.authentication = authentication
    return context
  }
}
