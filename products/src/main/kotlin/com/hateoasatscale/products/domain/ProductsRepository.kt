package com.hateoasatscale.products.domain

fun interface ProductsRepository {
    fun findBy(id: Long): Product
}