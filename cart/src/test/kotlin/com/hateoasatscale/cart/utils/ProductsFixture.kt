package com.hateoasatscale.cart.utils

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductsStartupDto
import org.springframework.hateoas.Link

class ProductsFixture {
    companion object {
        val startup = ProviderProductsStartupDto()
        val apple: ProviderProductDto = ProviderProductDto("apple", "1.00".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/apple").withSelfRel())
        val pineapple: ProviderProductDto = ProviderProductDto("pineapple", "200.50".toBigDecimal())
            .add(Link.of("http://localhost:8080/products/pineapple").withSelfRel())
    }
}
