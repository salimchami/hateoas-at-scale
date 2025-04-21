package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

data class ProviderProductDto(
    val name: String,
    val price: BigDecimal,
) : RepresentationModel<ProviderProductDto>()