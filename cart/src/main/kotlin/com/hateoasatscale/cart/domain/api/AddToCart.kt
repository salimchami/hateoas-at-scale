package com.hateoasatscale.cart.domain.api

import com.hateoasatscale.cart.domain.entities.add.ProductToAdd

fun interface AddToCart {
    fun theProduct(username: String, productToAdd: ProductToAdd)
}
