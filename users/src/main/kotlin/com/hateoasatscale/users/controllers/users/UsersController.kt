package com.hateoasatscale.users.controllers.users

import com.hateoasatscale.users.config.UrlServiceResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController(@Autowired private val serviceResolver: UrlServiceResolver, @Value("\${spring.application.name}") private val appName: String) {

    @GetMapping("/{id}")
    fun userInfo(@PathVariable id: Long): HttpEntity<User> {
        val usersAppUrl = this.serviceResolver.getServiceUrl(appName)
        return HttpEntity<User>(User(usersAppUrl, id, "ada.lovelace", "Ada", "lovelace"))
    }
}
