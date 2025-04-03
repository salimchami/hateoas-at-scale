package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsProvider
import org.springframework.stereotype.Component

@Component
class ProductsAdapter(
    private val productsProvider: ProductsProvider
) : ProductsRepository {
    override fun findBy(ids: List<Long>): List<Product> {
        val products = productsProvider.findBy(ids)
        return products.map { Product(it.name, it.reference, it.price) }
    }
}