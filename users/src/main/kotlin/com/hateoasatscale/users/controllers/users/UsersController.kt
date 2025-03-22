package com.hateoasatscale.users.controllers.users

import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UsersController {

    @GetMapping("/{id}")
    fun userInfo(@PathVariable id: Long): HttpEntity<User> {
        return HttpEntity<User>(User("ada.lovelace", "Ada", "lovelace"))
    }
}
