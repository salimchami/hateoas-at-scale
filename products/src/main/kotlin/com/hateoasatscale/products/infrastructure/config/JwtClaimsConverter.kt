package com.hateoasatscale.products.infrastructure.config

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component

@Component
class JwtClaimsConverter : Converter<Jwt?, AbstractAuthenticationToken?> {
  override fun convert(jwt: Jwt): AbstractAuthenticationToken {
    val authorities = extractRealmRoles(jwt)
    return JwtAuthenticationToken(jwt, authorities, getPrincipalFromClaim(jwt))
  }

  private fun getPrincipalFromClaim(jwt: Jwt): String {
    val claimName = "preferred_username"
    return jwt.getClaim(claimName)
  }

  private fun extractRealmRoles(jwt: Jwt): Collection<GrantedAuthority> {
    val resource = jwt.getClaim<Map<String, Any>>("realm_access")
    val roles = resource?.get("roles") as? Collection<*> ?: return setOf()
    return roles.map { role -> SimpleGrantedAuthority("ROLE_$role") }.toSet()
  }
}
