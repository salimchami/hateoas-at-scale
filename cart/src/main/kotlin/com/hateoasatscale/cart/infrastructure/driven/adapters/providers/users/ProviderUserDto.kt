package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.hateoas.RepresentationModel

data class ProviderUserDto(
    val username: String,
) : RepresentationModel<ProviderUserDto>()