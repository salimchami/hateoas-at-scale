package com.hateoasatscale.cart.utils

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.ProviderUserDto
import org.springframework.hateoas.Link

class UsersFixture {
    companion object {
        val adaLovelace = ProviderUserDto("ada.lovelace", "Ada", "Lovelace")
            .add(Link.of("http://users.localhost:8080/users/1").withSelfRel())
    }
}