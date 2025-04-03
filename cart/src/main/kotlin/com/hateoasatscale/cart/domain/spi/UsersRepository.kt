package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.User

fun interface UsersRepository {
    fun findBy(id: Long): User
}