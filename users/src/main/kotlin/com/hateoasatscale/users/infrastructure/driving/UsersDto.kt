package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class UsersDto @JsonCreator constructor(
    private val username: String,
    val list: List<UserDto>,
) : RepresentationModel<UsersDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(UsersResource::class.java.methods.first { it.name == "findAll" }).withSelfRel())
    }
}
