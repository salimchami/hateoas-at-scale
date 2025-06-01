package com.hateoasatscale.products.domain

class ProductsSearch(private val productsRepository: ProductsRepository) : FindProducts {
    @Throws(ProductNotFound::class)
    override fun all(): List<Product> {
        return productsRepository.findAll()
    }

    @Throws(ProductNotFound::class)
    override fun some(names: List<String>): List<Product> {
        return productsRepository.findAllByNames(names)
    }
}
