package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.cart.domain.share.network.Link
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

class ProductDto @JsonCreator constructor(
    val name: String,
    val reference: String,
    val price: BigDecimal,
    private val links: List<Link>
) : RepresentationModel<ProductDto>() {

    init {
        addLinks(links)
    }

    private fun addLinks(links: List<Link>) {
        links.forEach { link -> add(link.rel?.let { org.springframework.hateoas.Link.of(link.href, it) }) }
    }
}