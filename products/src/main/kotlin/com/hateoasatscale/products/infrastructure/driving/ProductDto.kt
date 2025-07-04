package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class ProductDto @JsonCreator constructor(
    private val cartsLinks: List<Link>,
    val name: String,
    val reference: String,
    val price: BigDecimal
) : RepresentationModel<ProductDto>() {

    init {
        addSelfLink()
        addStartupLinksFromCarts()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(ProductsResource::class.java).find(name)).withSelfRel())
    }

    private fun addStartupLinksFromCarts() {
        cartsLinks.forEach { link -> add(link) }
    }
}
