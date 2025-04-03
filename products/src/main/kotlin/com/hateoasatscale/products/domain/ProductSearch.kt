package com.hateoasatscale.products.domain

class ProductSearch(private val productsRepository: ProductsRepository) : FindProduct {
    @Throws(ProductNotFound::class)
    override fun by(id: Long): Product {
        return productsRepository.findBy(id)
    }
}