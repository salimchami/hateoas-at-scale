package com.hateoasatscale.users.controllers.users

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

class User @JsonCreator constructor(
    private val usersServiceUrl: String,
    private val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<User>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(Link.of("$usersServiceUrl/users/$id"))
    }
}