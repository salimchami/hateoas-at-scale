package com.hateoasatscale.products.domain

import com.hateoasatscale.products.infrastructure.driven.ProductsRepository

class ProductSearch(private val productsRepository: ProductsRepository) : FindProduct {
    @Throws(ProductNotFound::class)
    override fun by(id: Long): Product {
        return productsRepository.findBy(id)
    }
}