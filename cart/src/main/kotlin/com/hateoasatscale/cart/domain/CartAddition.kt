package com.hateoasatscale.cart.domain

import com.hateoasatscale.cart.domain.api.AddToCart
import com.hateoasatscale.cart.domain.entities.add.ProductToAdd
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.domain.spi.ProductsRepository

class CartAddition(val productsRepository: ProductsRepository,
    val cartRepository: CartsRepository): AddToCart {
    override fun theProduct(
        username: String,
        productToAdd: ProductToAdd
    ) {
        val product = this.productsRepository.findBy(productToAdd.name)
        this.cartRepository.updateProductQuantity(username, product.name, productToAdd.quantity)
    }
}
