package com.hateoasatscale.cart.infrastructure.config

import com.hateoasatscale.cart.domain.CartSearch
import com.hateoasatscale.cart.domain.spi.CartsRepository
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.domain.spi.UsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Injections {

    @Bean
    fun FindCart(
        cartsRepository: CartsRepository,
        productsRepository: ProductsRepository,
        usersRepository: UsersRepository
    ) = CartSearch(cartsRepository, productsRepository, usersRepository)
}