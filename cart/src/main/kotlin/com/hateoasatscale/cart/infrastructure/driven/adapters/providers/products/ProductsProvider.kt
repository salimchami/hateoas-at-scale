package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

fun interface ProductsProvider {
    fun findBy(ids: List<Long>): List<ProviderProductDto>
}