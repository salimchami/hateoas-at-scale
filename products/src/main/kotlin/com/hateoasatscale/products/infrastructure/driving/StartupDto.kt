package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo


class StartupDto @JsonCreator constructor() : RepresentationModel<StartupDto>() {
    init {
        addProductsLink()
        addProductLink()
    }

    private fun addProductLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "find" }).withRel("product"))
    }

    private fun addProductsLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "findAll" }).withRel("products"))
    }
}
