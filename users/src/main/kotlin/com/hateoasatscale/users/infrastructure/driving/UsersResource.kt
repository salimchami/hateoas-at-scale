package com.hateoasatscale.users.infrastructure.driving

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("users")
fun interface UsersResource {

    @GetMapping("/users/{id}")
    fun userInfo(@PathVariable id: Long): EntityModel<UserDto>;
}
