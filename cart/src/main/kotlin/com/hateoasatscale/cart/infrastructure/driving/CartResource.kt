package com.hateoasatscale.cart.infrastructure.driving

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient("cart")
fun interface CartResource {

    @GetMapping("/cart/{id}")
    fun userInfo(@PathVariable id: Long): EntityModel<CartDto>;
}
