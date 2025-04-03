package com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users

import org.springframework.hateoas.RepresentationModel

class ProviderUserDto(
    private val username: String,
    val firstname: String,
    val lastname: String
) : RepresentationModel<ProviderUserDto>()