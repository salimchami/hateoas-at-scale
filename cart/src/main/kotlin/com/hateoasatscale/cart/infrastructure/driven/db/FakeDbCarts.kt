package com.hateoasatscale.cart.infrastructure.driven.db

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.ProviderUserDto

class FakeDbCarts {
    val myMap = hashMapOf<ProviderUserDto, List<ProviderProductDto>>()
    fun add(user: ProviderUserDto, products: List<ProviderProductDto>) {
        this.myMap.put(user, products)
    }

    fun findBy(username: String): List<ProviderProductDto> {
        return this.myMap.filter { it.key.username == username }.flatMap { it.value }
    }


}