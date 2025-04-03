package com.hateoasatscale.cart.domain.entities

import java.math.BigDecimal

class UserCart(
    val id: Long,
    val user: User,
    val products: List<Product>,
) {
    val totalPrice: BigDecimal = products.sumOf { it.price }
}

