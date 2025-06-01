package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductsAdapter(
    private val productsFeignClient: ProductsFeignClient,
    private val restTemplate: RestTemplate
) : ProductsRepository {
    override fun findBy(names: List<String>): List<Product> {
        return names.map { name -> this.findBy(name) }
    }

    override fun findBy(name: String): Product {
        val startupLinks = productsFeignClient.startup()
        // TODO: add HateoasLinks enum everywhere
        val productLink = startupLinks.find { link -> link.rel.value() == "product" }
            ?: throw IllegalStateException("Product link not found in startup response")

        val productResponse = restTemplate.getForObject(productLink.href, ProviderProductDto::class.java)
            ?: throw IllegalStateException("Product not found for name: $name")

        return Product(
            productResponse.name,
            productResponse.price,
            productResponse.links.map { link -> Link(link.href, link.rel.value()) },
        )

    }
}
