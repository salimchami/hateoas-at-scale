package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class ProductsDto @JsonCreator constructor(
    val list: List<ProductDto>
) : RepresentationModel<ProductsDto>() {

    init {
        addSelfLink()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(ProductsResource::class.java).findAll()).withSelfRel())
    }
}
