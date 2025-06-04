package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.products.infrastructure.hateoas.WorkflowLinks
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class StartupDto @JsonCreator constructor() : RepresentationModel<StartupDto>() {
    init {
        addProductLink()
        addProductsLink()
        addSomeProductsLink()
    }

    private fun addSomeProductsLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "findSome" }).withRel(WorkflowLinks.SOME_PRODUCTS))
    }

    private fun addProductLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "find" }).withRel(WorkflowLinks.PRODUCT))
    }

    private fun addProductsLink() {
        add(linkTo(ProductsResource::class.java.methods.first { it.name == "findAll" }).withRel(WorkflowLinks.ALL_PRODUCTS))
    }
}
