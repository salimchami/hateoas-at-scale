package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.api.FindUser
import com.hateoasatscale.users.domain.api.FindUsers
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
) {

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping("/user-info")
    fun userInfo(@AuthenticationPrincipal principal: Jwt): ResponseEntity<UserDto> {
        val username = principal.claims["preferred_username"] as String
        val user = findUser.by(username)
        val userDto = this.userMapping.domainToDto(user)
        return ResponseEntity.ok(userDto)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    fun findAll(@AuthenticationPrincipal principal: Jwt): ResponseEntity<UsersDto> {
        val username = principal.claims["preferred_username"] as String
        val users = findUsers.all(username)
        val usersDto = this.userMapping.domainToDto(users)
        return ResponseEntity.ok(UsersDto(username, usersDto))
    }
}
