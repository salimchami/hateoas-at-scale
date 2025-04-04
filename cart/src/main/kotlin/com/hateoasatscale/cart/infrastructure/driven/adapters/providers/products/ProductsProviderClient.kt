package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Component
class ProductsProviderClient(val restTemplate: RestTemplate) :
    ProductsProvider {
    override fun findBy(ids: List<Long>): List<ProviderProductDto> {
        return ids.map { id -> this.restTemplate.getForObject<ProviderProductDto>("http://products/products/${id}") }
    }
}