package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.api.FindUser
import com.hateoasatscale.users.domain.api.FindUsers
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
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
        request: HttpServletRequest
    ): ResponseEntity<UserDto> {
        println("Request URL: ${request.requestURL}")
        println("Request URI: ${request.requestURI}")
        println("Server Name: ${request.serverName}")
        println("Server Port: ${request.serverPort}")
        println("Scheme: ${request.scheme}")
        println("X-Forwarded-Host: ${request.getHeader("X-Forwarded-Host")}")
        println("X-Forwarded-Port: ${request.getHeader("X-Forwarded-Port")}")
        println("X-Forwarded-Proto: ${request.getHeader("X-Forwarded-Proto")}")
        println("X-Forwarded-Prefix: ${request.getHeader("X-Forwarded-Prefix")}")

        val username = principal.claims["preferred_username"] as String
        val user = findUser.by(username)
        val productsStartupLinks = productsFeignClient.startupLinks()
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
