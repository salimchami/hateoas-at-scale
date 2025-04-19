package com.hateoasatscale.users.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class UserDto @JsonCreator constructor(
    private val gatewayUrl: String,
    private val productsServiceName: String,
    private val productsEndpoint: String,
    private val cartsServiceName: String,
    private val cartsEndpoint: String,
    val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<UserDto>() {

    init {
        addSelfLink()
        addProductsLink()
        addCartLink()
    }

    private fun addProductsLink() {
        add(Link.of("$gatewayUrl/$productsServiceName/$productsEndpoint", "products"))
    }

    private fun addCartLink() {
        add(Link.of("$gatewayUrl/$cartsServiceName/$cartsEndpoint/$username", "cart"))
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(UsersResource::class.java)
            .userInfo(this.username)).withSelfRel())
    }
}
