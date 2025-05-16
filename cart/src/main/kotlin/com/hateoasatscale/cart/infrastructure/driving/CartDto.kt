package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class CartDto @JsonCreator constructor(
    val totalPrice: BigDecimal,
    val user: UserDto,
    val products: List<ProductDto>,
) : RepresentationModel<CartDto>() {
    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(CartResource::class.java.methods.first { it.name == "cartInfo" }).withSelfRel())
    }
}
