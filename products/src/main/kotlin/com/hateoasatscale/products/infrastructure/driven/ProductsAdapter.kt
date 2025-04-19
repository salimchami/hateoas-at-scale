package com.hateoasatscale.products.infrastructure.driven

import com.hateoasatscale.products.domain.Product
import com.hateoasatscale.products.domain.ProductNotFound
import com.hateoasatscale.products.domain.ProductsRepository
import org.springframework.stereotype.Component

@Component
class ProductsAdapter : ProductsRepository {
    @Throws(ProductNotFound::class)
    override fun findBy(id: Long): Product {
        return FakeDbProducts.products.find { it.id == id }
            ?.let { Product(it.id, it.name, it.reference, it.price) }
            ?: throw ProductNotFound("Product with id $id not found")
    }

    override fun findAll(): List<Product> {
        return FakeDbProducts.products.map { Product(it.id, it.name, it.reference, it.price) }
    }
}