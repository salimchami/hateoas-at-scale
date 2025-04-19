package com.hateoasatscale.products.domain

interface ProductsRepository {
    fun findBy(id: Long): Product
    fun findAll(): List<Product>
}