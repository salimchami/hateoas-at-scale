package com.hateoasatscale.cart.domain

import com.hateoasatscale.cart.domain.api.FindCart
import com.hateoasatscale.cart.domain.entities.UserCart
import com.hateoasatscale.cart.domain.errors.CartNotFound
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.domain.spi.UsersRepository

class CartSearch(
    private val cartsRepository: CartsRepository,
    private val productsRepository: ProductsRepository,
    private val usersRepository: UsersRepository,
) : FindCart {
    @Throws(CartNotFound::class)
    override fun by(id: Long): UserCart {
        val cart = cartsRepository.findBy(id)
        val user = usersRepository.findBy(cart.userId)
        val products = productsRepository.findBy(cart.productsId)
        return UserCart(cart.id, user, products)
    }
}