package com.hateoasatscale.cart.infrastructure.driven.db

import com.hateoasatscale.cart.infrastructure.driven.entities.DbCart

class FakeDbCarts {
    companion object {
        val carts = listOf(
            DbCart(1, 1, listOf(1, 4)),
            DbCart(2, 2, listOf(5, 6, 7)),
        )
    }
}