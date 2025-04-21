package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.User

fun interface UsersRepository {
    fun findBy(username: String): User
}