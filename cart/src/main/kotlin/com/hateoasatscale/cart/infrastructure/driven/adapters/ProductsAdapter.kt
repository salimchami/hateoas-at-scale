package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.Product
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.ProductsRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProvidersProductDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class ProductsAdapter(
    private val productsFeignClient: ProductsFeignClient,
    private val restTemplate: RestTemplate
) : ProductsRepository {
    override fun findBy(names: List<String>): List<Product> {
        val productsResponse = findProductsByName(names)
        return productsResponse.list.map {
            Product(
                it.name,
                it.price,
                it.links.map { link -> Link(link.href, link.rel.value()) },
            )
        }
    }

    private fun findProductsByName(
        names: List<String>
    ): ProvidersProductDto {
        val startupLinks = productsFeignClient.startup()
        val productsLink = startupLinks.find { link -> link.rel.value() == "someProducts" }
            ?: throw IllegalStateException("Products link not found in products-service startup response")
        val baseUrl = productsLink.href.replace("?name={name}", "")
        val nameParams = names.joinToString("&") { "name=$it" }
        val finalUrl = "$baseUrl?$nameParams"
        return restTemplate.getForObject(transformToServiceUrl(finalUrl), ProvidersProductDto::class.java)
            ?: throw IllegalStateException("Products not found for names: $names")
    }

    override fun findBy(name: String): Product {
        return this.findBy(listOf(name))[0]
    }

    fun transformToServiceUrl(url: String): String {
        val original = UriComponentsBuilder.fromUriString(url).build()
        val pathSegments = original.pathSegments

        val serviceName = pathSegments.firstOrNull { it.endsWith("-service") }

        return if (serviceName != null) {
            val serviceIndex = pathSegments.indexOf(serviceName)
            val remainingSegments = pathSegments.drop(serviceIndex + 1)

            UriComponentsBuilder.newInstance()
                .scheme(original.scheme)
                .host(serviceName)
                .pathSegment(*remainingSegments.toTypedArray())
                .query(original.query)
                .fragment(original.fragment)
                .build()
                .toUriString()
        } else {
            url
        }
    }
}
