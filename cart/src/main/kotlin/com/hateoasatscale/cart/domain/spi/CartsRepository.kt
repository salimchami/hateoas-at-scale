package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.Cart

fun interface CartsRepository {
    fun findBy(id: Long): Cart
}