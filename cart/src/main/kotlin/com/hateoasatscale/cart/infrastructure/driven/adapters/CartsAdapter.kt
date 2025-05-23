package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Cart
import com.hateoasatscale.cart.domain.entities.CartProduct
import com.hateoasatscale.cart.domain.errors.CartNotFound
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.infrastructure.driven.db.FakeDbCarts
import org.springframework.stereotype.Component

@Component
class CartsAdapter : CartsRepository {
    val db = FakeDbCarts()

    @Throws(CartNotFound::class)
    override fun findBy(username: String): Cart {
        val dbProducts = db.findBy(username)
        return Cart(username, dbProducts.map { product -> CartProduct(product.name, product.quantity) })
    }

    override fun updateProductQuantity(username: String, productName: String, quantity: Int) {
        db.updateProduct(username, productName, quantity)
    }
}
