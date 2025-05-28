package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn


class StartupDto @JsonCreator constructor() : RepresentationModel<StartupDto>() {
    init {
        addProductsLink()
    }

    private fun addProductsLink() {
        add(linkTo(methodOn(ProductsResource::class.java).findAll()).withRel("products"))
    }
}
