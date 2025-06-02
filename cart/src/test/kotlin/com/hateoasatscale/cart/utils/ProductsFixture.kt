package com.hateoasatscale.cart.utils

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import org.springframework.hateoas.Link

class ProductsFixture {
    companion object {
        val startupLinks = listOf(Link.of("http://localhost/api/v1/products", "allProducts"),
            Link.of("http://localhost/api/v1/products/some?name={name}", "someProducts"))
        val apple: ProviderProductDto = ProviderProductDto("apple", "1.00".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/apple").withSelfRel())
        val pineapple: ProviderProductDto = ProviderProductDto("pineapple", "200.50".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/pineapple").withSelfRel())
    }
}
