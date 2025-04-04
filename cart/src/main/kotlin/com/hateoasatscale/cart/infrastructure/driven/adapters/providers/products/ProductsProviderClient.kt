package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class ProductsProviderClient(val restTemplate: RestTemplate, val consulDiscoveryClient: ConsulDiscoveryClient) :
    ProductsProvider {
    override fun findBy(ids: List<Long>): List<ProviderProductDto> {
        val usersService =
            consulDiscoveryClient.getInstances("products").firstOrNull()
                ?: throw Exception("No products service available")
        val productsServiceUrl = usersService.uri.toString()
        return ids.map { this.restTemplate.getForObject<ProviderProductDto>("$productsServiceUrl/products/$it.id") }
    }
}