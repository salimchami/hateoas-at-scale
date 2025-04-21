package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Cart
import com.hateoasatscale.cart.domain.errors.CartNotFound
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.ProviderUserDto
import com.hateoasatscale.cart.infrastructure.driven.db.FakeDbCarts
import org.springframework.stereotype.Component

@Component
class CartsAdapter : CartsRepository {
    val db = FakeDbCarts()

    @Throws(CartNotFound::class)
    override fun findBy(username: String): Cart {
        return db.myMap.filter { it.key == ProviderUserDto(username) }
            .map { item -> Cart(item.key.username, item.value.map { it.name}) }
            .firstOrNull()
            ?: throw CartNotFound("Cart for user $username not found")
    }
}