package com.hateoasatscale.products.domain

class ProductsSearch(private val productsRepository: ProductsRepository) : FindProducts {
    @Throws(ProductNotFound::class)
    override fun all(): List<Product> {
        return productsRepository.findAll()
    }
}