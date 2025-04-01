package com.hateoasatscale.cart.controllers.cart

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

class Product @JsonCreator constructor(
    private val usersServiceUrl: String,
    private val id: Long,
    val name: String,
    val reference: String,
    val price: BigDecimal,
) : RepresentationModel<Product>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(Link.of("$usersServiceUrl/products/$id"))
    }
}