package com.hateoasatscale.cart.utils

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import org.springframework.hateoas.Link

class ProductsFixture {
    companion object {
        val apple: ProviderProductDto = ProviderProductDto("apple", "RRDGHGT554346", "1.00".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/1").withSelfRel())
        val orange: ProviderProductDto = ProviderProductDto("orange", "DFHDHYUJJBN887644", "158.00".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/4").withSelfRel())
    }
}