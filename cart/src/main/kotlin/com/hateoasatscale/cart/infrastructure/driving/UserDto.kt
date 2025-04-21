package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.cart.domain.share.network.Link
import org.springframework.hateoas.RepresentationModel

class UserDto @JsonCreator constructor(val username: String, private val links: List<Link>) :
    RepresentationModel<UserDto>() {
    init {
        addLinks(links)
    }

    private fun addLinks(links: List<Link>) {
        links.forEach { link -> link.rel?.let { org.springframework.hateoas.Link.of(link.href, it) }?.let { add(it) } }
    }
}