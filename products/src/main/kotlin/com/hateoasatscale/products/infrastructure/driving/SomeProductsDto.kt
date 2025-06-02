package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class SomeProductsDto @JsonCreator constructor(
    val list: List<ProductDto>
) : RepresentationModel<SomeProductsDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "findSome" }).withSelfRel())
    }
}
