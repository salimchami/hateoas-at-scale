package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products

import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

class ProviderProductDto(
    val name: String,
    val reference: String,
    val price: BigDecimal
) : RepresentationModel<ProviderProductDto>()