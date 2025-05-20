package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonCreator
import org.springframework.hateoas.RepresentationModel

data class ProductToUpdateDto @JsonCreator constructor(
    val name: String,
    val quantity: Int
) : RepresentationModel<ProductToUpdateDto>()
