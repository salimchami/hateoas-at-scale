package com.hateoasatscale.cart.domain.entities

import java.math.BigDecimal

data class UserCart(
    val username: String,
    val products: List<Product>,
) {
    val totalPrice: BigDecimal = products.sumOf { it.price }
}

