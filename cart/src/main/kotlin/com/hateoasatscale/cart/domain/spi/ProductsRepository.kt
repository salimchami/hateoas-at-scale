package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.Product

fun interface ProductsRepository {
    fun findBy(id: List<Long>): List<Product>
}