package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import com.hateoasatscale.cart.infrastructure.hateoas.WorkflowLinks
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo

class StartupDto @JsonCreator constructor() : RepresentationModel<StartupDto>() {
    init {
        addMyCartLink()
        addAddToCartLink()
    }

    private fun addAddToCartLink() {
        add(linkTo(CartResource::class.java.methods.first { it.name == "myCart" }).withRel(WorkflowLinks.MY_CART))
    }

    private fun addMyCartLink() {
        add(linkTo(CartResource::class.java.methods.first { it.name == "addToCart" }).withRel(WorkflowLinks.ADD_PRODUCT_TO_CART))
    }
}
