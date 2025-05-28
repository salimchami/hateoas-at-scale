package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.api.FindUser
import com.hateoasatscale.users.domain.api.FindUsers
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UsersResource(
    private val findUser: FindUser,
    private val findUsers: FindUsers,
    private val userMapping: UserMapping,
    private val productsFeignClient: ProductsFeignClient,
    private val cartsFeignClient: CartsFeignClient,
) {

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping("/user-info")
    fun userInfo(
        @AuthenticationPrincipal principal: Jwt,
        @RequestHeader headers: Map<String, String>
    ): ResponseEntity<UserDto> {
        val username = principal.claims["preferred_username"] as String
        val user = findUser.by(username)
        val feignClientHeaders = headers.filter {
            it.key.lowercase() == "authorization" || it.key.lowercase().startsWith("x-forwarded-")|| it.key.lowercase() == "x-service-path"
        }
        val productsStartupLinks = productsFeignClient.startupLinks(
            feignClientHeaders + mapOf(

                "X-Forwarded-Prefix" to "/products-service",
                "X-Forwarded-Host" to "172.25.0.10",
                "X-Forwarded-Port" to "8000",
                "X-Forwarded-Proto" to "http"
            ),
        )
        val cartsStartupLinks = cartsFeignClient.startupLinks()
        val userDto = this.userMapping.domainToDto(user, productsStartupLinks, cartsStartupLinks)
        return ResponseEntity.ok(userDto)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    fun findAll(@AuthenticationPrincipal principal: Jwt): ResponseEntity<UsersDto> {
        val username = principal.claims["preferred_username"] as String
        val users = findUsers.all(username)
        val usersDto = this.userMapping.domainToInfoDto(users)
        return ResponseEntity.ok(UsersDto(username, usersDto))
    }
}
