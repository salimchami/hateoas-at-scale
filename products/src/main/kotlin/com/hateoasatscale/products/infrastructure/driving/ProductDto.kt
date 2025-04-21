package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import java.math.BigDecimal

class ProductDto @JsonCreator constructor(
    val name: String,
    val reference: String,
    val price: BigDecimal
) : RepresentationModel<ProductDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(ProductsResource::class.java).find(name)).withSelfRel())
    }
}
