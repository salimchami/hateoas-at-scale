package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.hateoas.RepresentationModel

data class ProviderProductsDto(
    val list: List<ProviderProductDto>,
) : RepresentationModel<ProviderProductsDto>()
