package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.hateoas.RepresentationModel

data class ProvidersProductDto(
    val list: List<ProviderProductDto>,
) : RepresentationModel<ProvidersProductDto>()
