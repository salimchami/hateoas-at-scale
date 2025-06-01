package com.hateoasatscale.products.domain

interface ProductsRepository {
    fun findBy(name: String): Product
    fun findAll(): List<Product>
    fun findAllByNames(names: List<String>): List<Product>
}
