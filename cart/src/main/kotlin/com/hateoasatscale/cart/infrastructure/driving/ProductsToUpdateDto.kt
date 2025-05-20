package com.hateoasatscale.cart.infrastructure.driving

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
 data class ProductsToUpdateDto(@JsonProperty("products")
    val products: List<ProductToUpdateDto>,
) : RepresentationModel<ProductsToUpdateDto>()
