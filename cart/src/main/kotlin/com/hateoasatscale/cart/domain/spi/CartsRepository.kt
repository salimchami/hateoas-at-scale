package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.Cart

interface CartsRepository {
    fun findBy(username: String): Cart
    fun updateProductQuantity(username: String, productName: String, quantity: Int)
}
