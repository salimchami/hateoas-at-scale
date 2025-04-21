package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.Cart

fun interface CartsRepository {
    fun findBy(username: String): Cart
}