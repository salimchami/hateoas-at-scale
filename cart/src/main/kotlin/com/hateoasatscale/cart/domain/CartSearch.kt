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
    override fun findBy(username: String): UserCart {
        val dbCart = cartsRepository.findBy(username)
        val user = usersRepository.findBy(dbCart.username)
        val products = productsRepository.findBy(dbCart.productsNames)
        return UserCart(user.username, products)
    }
}