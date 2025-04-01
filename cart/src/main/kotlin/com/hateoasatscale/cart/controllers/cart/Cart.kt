package com.hateoasatscale.cart.controllers.cart

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

class Cart @JsonCreator constructor(
    private val usersServiceUrl: String,
    private val userId: Long,
    val products: List<Product>,
    val totalPrice: BigDecimal,
) : RepresentationModel<Cart>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(Link.of("$usersServiceUrl/cart/$userId"))
    }
}