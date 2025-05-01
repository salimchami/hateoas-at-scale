package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.api.FindUser
import com.hateoasatscale.users.domain.api.FindUsers
import jakarta.validation.constraints.NotBlank
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersResource(
    private val findUser: FindUser,
    private val findUsers: FindUsers,
    private val userMapping: UserMapping,
) {

    @GetMapping("/users/{username}")
    fun userInfo(@PathVariable @NotBlank username: String): EntityModel<UserDto> {
        val user = findUser.by(username)
        val userDto = this.userMapping.domainToDto(user)
        return EntityModel.of(userDto)
    }

    @GetMapping("users/all/{username}")
    fun findAll(@PathVariable @NotBlank username: String): EntityModel<UsersDto> {
        val users = findUsers.all(username)
        val usersDto = this.userMapping.domainToDto(users)
        return EntityModel.of(UsersDto(username, usersDto))
    }
}
