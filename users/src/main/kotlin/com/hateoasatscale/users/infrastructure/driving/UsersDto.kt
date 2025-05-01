package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class UsersDto @JsonCreator constructor(
    private val username: String,
    val users: List<UserDto>,
) : RepresentationModel<UsersDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(
            linkTo(
                methodOn(UsersResource::class.java)
                    .findAll(username)
            ).withSelfRel()
        )
    }
}
