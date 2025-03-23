package com.hateoasatscale.users.controllers.users

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class User @JsonCreator constructor(
    private val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<User>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(UsersController::class.java).userInfo(id)).withSelfRel())
    }
}