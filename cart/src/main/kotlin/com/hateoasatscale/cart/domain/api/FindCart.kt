package com.hateoasatscale.cart.domain.api

import com.hateoasatscale.cart.domain.entities.UserCart

fun interface FindCart {
    fun by(id: Long): UserCart
}
