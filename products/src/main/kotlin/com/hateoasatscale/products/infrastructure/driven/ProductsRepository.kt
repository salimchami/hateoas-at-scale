package com.hateoasatscale.products.infrastructure.driven

import com.hateoasatscale.products.domain.Product

fun interface ProductsRepository {
    fun findBy(id: Long): Product
}