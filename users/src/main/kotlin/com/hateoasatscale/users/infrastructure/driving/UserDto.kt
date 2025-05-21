package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.users.domain.Permission
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class UserDto @JsonCreator constructor(
    private val productsLinks: List<Link>,
    private val cartsLinks: List<Link>,
    private val permissions: List<Permission>,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<UserDto>() {

    init {
        addSelfLink()
        addAllUsersLink()
        addFirstLinksFromProducts()
        addFirstLinksFromCarts()
    }

    private fun addFirstLinksFromCarts() {
        cartsLinks.forEach { link -> add(link) }
    }

    private fun addFirstLinksFromProducts() {
        productsLinks.forEach { link -> add(link) }
    }

    private fun addAllUsersLink() {
        if (permissions.contains(Permission.READ_ALL_USERS)) {
            add(linkTo(UsersResource::class.java.methods.first { it.name == "findAll" }).withRel("all-users"))
        }
    }

    private fun addSelfLink() {
        if (permissions.containsAll(listOf(Permission.READ_OWN_USER))) {
            add(
                linkTo(UsersResource::class.java.methods.first { it.name == "userInfo" }).withSelfRel(),
            )
        }
    }
}
