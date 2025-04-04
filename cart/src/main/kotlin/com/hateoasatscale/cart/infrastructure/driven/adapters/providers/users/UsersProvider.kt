package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

fun interface UsersProvider {
    fun findBy(id: Long): ProviderUserDto
}