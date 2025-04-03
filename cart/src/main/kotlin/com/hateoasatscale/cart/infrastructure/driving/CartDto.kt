package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import java.math.BigDecimal

class CartDto @JsonCreator constructor(
    private val id: Long,
    val totalPrice: BigDecimal,
    val user: UserDto,
    val products: List<ProductDto>,
) : RepresentationModel<CartDto>() {
    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(CartResource::class.java).userInfo(id)).withSelfRel())
    }
}
