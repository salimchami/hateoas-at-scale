package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.security.core.context.SecurityContextHolder


class StartupDto @JsonCreator constructor() : RepresentationModel<StartupDto>() {
    init {
        addMyCartLink()
        addAddToCartLink()
    }

    private fun addAddToCartLink() {
        val jwt =
            SecurityContextHolder.getContext().authentication.principal as org.springframework.security.oauth2.jwt.Jwt
        add(linkTo(methodOn(CartResource::class.java).myCart(jwt)).withRel("my-cart"))
    }

    private fun addMyCartLink() {
        val jwt =
            SecurityContextHolder.getContext().authentication.principal as org.springframework.security.oauth2.jwt.Jwt
        add(linkTo(methodOn(CartResource::class.java).addToCart(jwt, ProductToUpdateDto("placeholder-name", 0)))
            .withRel("add-product"))
    }
}
