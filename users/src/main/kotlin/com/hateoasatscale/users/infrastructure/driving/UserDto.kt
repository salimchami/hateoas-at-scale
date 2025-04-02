package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel

class UserDto @JsonCreator constructor(
    private val usersServiceUrl: String,
    private val id: Long,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<UserDto>() {

    init {
        addSelfLink()
    }


    //    private fun addSelfLink() {
//        add(linkTo(methodOn(UsersController::class.java).userInfo(id)).withSelfRel())
//    }
    private fun addSelfLink() {
        add(Link.of("$usersServiceUrl/users/$id"))
    }
}
