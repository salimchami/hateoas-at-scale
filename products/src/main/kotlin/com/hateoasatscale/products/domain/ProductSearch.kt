package com.hateoasatscale.products.domain

class ProductSearch(private val productsRepository: ProductsRepository) : FindProduct {
    @Throws(ProductNotFound::class)
    override fun by(name: String): Product {
        return productsRepository.findBy(name)
    }
}