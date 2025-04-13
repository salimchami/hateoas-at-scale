package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.FindUser
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersResource(private val findUser: FindUser) {

    @GetMapping("/users/{id}")
    fun userInfo(@PathVariable id: Long): EntityModel<UserDto> {
        val user = findUser.by(id)
        return EntityModel.of(UserDto(id, user.username, user.firstname, user.lastname))
    }
}
