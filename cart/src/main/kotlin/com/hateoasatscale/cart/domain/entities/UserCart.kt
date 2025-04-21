package com.hateoasatscale.cart.domain.entities

import java.math.BigDecimal

data class UserCart(
    val username: String,
    val products: List<UserCartProduct>,
) {
    val totalPrice: BigDecimal = products.map { it.totalPrice }.sumOf { it }
}

