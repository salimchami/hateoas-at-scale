package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import org.springframework.stereotype.Component

@Component
class ProductsAdapter : ProductsRepository {
    override fun findBy(id: List<Long>): List<Product> {
        return listOf()
    }
}