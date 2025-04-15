package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.HateoasLinkRewriter
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsServiceClient
import org.springframework.stereotype.Component

@Component
class ProductsAdapter(
    private val productsServiceClient: ProductsServiceClient
) : ProductsRepository {
    override fun findBy(ids: List<Long>): List<Product> {
        val products = ids.map { id -> productsServiceClient.findBy(id) }
        return products.map { product ->
            val links = HateoasLinkRewriter.rewrite(product.links, "products-service")
            Product(
                product.name,
                product.reference,
                product.price,
                links.map { link -> Link(link.href, link.rel.value()) })
        }
    }
}