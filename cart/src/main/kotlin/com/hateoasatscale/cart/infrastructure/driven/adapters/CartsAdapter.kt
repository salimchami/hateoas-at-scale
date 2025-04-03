package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Cart
import com.hateoasatscale.cart.domain.errors.CartNotFound
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.infrastructure.driven.db.FakeDbCarts
import org.springframework.stereotype.Component

@Component
class CartsAdapter : CartsRepository {
    @Throws(CartNotFound::class)
    override fun findBy(id: Long): Cart {
        return FakeDbCarts.carts.find { it.id == id }
            ?.let { Cart(it.id, it.userId, it.productsId) }
            ?: throw CartNotFound("Cart with id $id not found")
    }
}