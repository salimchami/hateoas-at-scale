package com.hateoasatscale.cart.domain.spi

import com.hateoasatscale.cart.domain.entities.Product

interface ProductsRepository {
    fun findBy(names: List<String>): List<Product>
    fun findBy(name: String): Product
}
