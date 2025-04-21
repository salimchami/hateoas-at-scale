package com.hateoasatscale.cart.domain

import com.hateoasatscale.cart.domain.api.FindCart
import com.hateoasatscale.cart.domain.entities.*
import com.hateoasatscale.cart.domain.errors.CartNotFound
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.domain.spi.UsersRepository
import java.math.BigDecimal

class CartSearch(
    private val cartsRepository: CartsRepository,
    private val productsRepository: ProductsRepository,
    private val usersRepository: UsersRepository,
) : FindCart {
    @Throws(CartNotFound::class)
    override fun by(username: String): UserCart {
        val cart: Cart = cartsRepository.findBy(username)
        val user: User = usersRepository.findBy(cart.username)
        val products: List<Product> = productsRepository.findBy(cart.products.map { it.name })
        return UserCart(user.username, products.map {
            UserCartProduct(
                it.name,
                it.price.multiply(BigDecimal(cart.products.first { product -> product.name == it.name }.quantity)),
                cart.products.first { product -> product.name == it.name }.quantity,
                it.links
            )
        })
    }

}