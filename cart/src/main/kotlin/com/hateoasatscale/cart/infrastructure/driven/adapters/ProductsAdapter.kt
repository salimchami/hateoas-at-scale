package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import org.springframework.stereotype.Component

@Component
class ProductsAdapter(
    private val productsFeignClient: ProductsFeignClient
) : ProductsRepository {
    override fun findBy(names: List<String>): List<Product> {
        return names.map { name -> this.findBy(name) }
    }

    override fun findBy(name: String): Product {
        val product = productsFeignClient.findBy(name)
        return Product(
            product.name,
            product.price,
            product.links.map { link -> Link(link.href, link.rel.value()) },
        )
    }
}
