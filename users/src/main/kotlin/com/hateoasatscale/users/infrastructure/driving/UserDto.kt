package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.users.domain.Permission
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class UserDto @JsonCreator constructor(
    private val gatewayUrl: String,
    private val productsServiceName: String,
    private val productsEndpoint: String,
    private val cartsServiceName: String,
    private val cartsEndpoint: String,
    private val permissions: List<Permission>,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<UserDto>() {

    init {
        addSelfLink(permissions)
        addAllUsersLink(permissions)
        addProductsLink(permissions)
        addCartLink(permissions)
    }

    private fun addAllUsersLink(permissions: List<Permission>) {
        if (permissions.contains(Permission.READ_ALL_USERS)) {
            add(linkTo(UsersResource::class.java.methods.first { it.name == "findAll" }).withRel("all-users"))
        }
    }

    private fun addProductsLink(permissions: List<Permission>) {
        if (permissions.containsAll(listOf(Permission.READ_PRODUCTS))) {
            add(Link.of("$gatewayUrl/$productsServiceName$productsEndpoint", "products"))
        }
    }

    private fun addCartLink(permissions: List<Permission>) {
        if (permissions.containsAll(listOf(Permission.READ_CART))) {
            add(Link.of("$gatewayUrl/$cartsServiceName$cartsEndpoint", "cart"))
        }
    }

    private fun addSelfLink(permissions: List<Permission>) {
        if (permissions.containsAll(listOf(Permission.READ_OWN_USER))) {
            add(
                linkTo(UsersResource::class.java.methods.first { it.name == "userInfo" }).withSelfRel(),
            )
        }
    }
}
