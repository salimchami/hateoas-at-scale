package com.hateoasatscale.products.infrastructure.driving

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("products")
fun interface ProductsResource {

    @GetMapping("/products/{id}")
    fun userInfo(@PathVariable id: Long): EntityModel<ProductDto>;
}
