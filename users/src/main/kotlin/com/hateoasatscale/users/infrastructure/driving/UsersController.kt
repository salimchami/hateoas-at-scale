package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.domain.FindUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(
    @Autowired private val serviceResolver: UrlServiceResolver,
    @Value("\${spring.application.name}") private val appName: String,
    @Autowired private val findUser: FindUser
) {

    @GetMapping("/{id}")
    fun userInfo(@PathVariable id: Long): HttpEntity<UserDto> {
        val usersAppUrl = this.serviceResolver.getServiceUrl(appName)
        val user = findUser.by(id)
        return HttpEntity<UserDto>(UserDto(usersAppUrl, id, user.username, user.firstname, user.lastname))
    }
}
