package com.hateoasatscale.products.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal
import org.springframework.hateoas.Link
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

class ProductDto @JsonCreator constructor(
    private val gatewayUrl: String,
    private val cartsServiceName: String,
    private val addProductToCartEndpoint: String,
    val name: String,
    val reference: String,
    val price: BigDecimal
) : RepresentationModel<ProductDto>() {

    init {
        addSelfLink()
        addAddProductToCartLink()
    }

    private fun addSelfLink() {
        add(linkTo(methodOn(ProductsResource::class.java).find(name)).withSelfRel())
    }

    private fun addAddProductToCartLink() {
        add(Link.of("$gatewayUrl/$cartsServiceName$addProductToCartEndpoint", "add-to-cart"))
    }
}
