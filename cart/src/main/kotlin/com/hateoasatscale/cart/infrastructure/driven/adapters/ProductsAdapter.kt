package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProvidersProductDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductsAdapter(
    private val productsFeignClient: ProductsFeignClient,
    private val restTemplate: RestTemplate
) : ProductsRepository {
    override fun findBy(names: List<String>): List<Product> {
        val startupLinks = productsFeignClient.startup()
        val productsLink = startupLinks.find { link -> link.rel.value() == "someProducts" }
            ?: throw IllegalStateException("Products link not found in products-service startup response")
        val queryParams = mapOf("name" to names.joinToString(","))
        val productsResponse = restTemplate.getForObject(productsLink.href, ProvidersProductDto::class.java, queryParams)
            ?: throw IllegalStateException("Products not found for names: $names")
        return productsResponse.list.map {
            Product(
                it.name,
                it.price,
                it.links.map { link -> Link(link.href, link.rel.value()) },
            )
        }
    }

    override fun findBy(name: String): Product {
        return this.findBy(listOf(name))[0]
    }
}
