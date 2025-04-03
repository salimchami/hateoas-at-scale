package com.hateoasatscale.cart.domain.entities

class Cart(
    val id: Long,
    val userId: Long,
    val productsId: List<Long>,
) {
}
