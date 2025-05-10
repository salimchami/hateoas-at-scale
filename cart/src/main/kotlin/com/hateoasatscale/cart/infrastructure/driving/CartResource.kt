package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.FindCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.*

@RestController
class CartResource(@Autowired private val findCart: FindCart) {

    @GetMapping("/carts/{username}")
    fun cartInfo(@PathVariable username: String): EntityModel<CartDto> {
        val cart = findCart.by(username)
        val user = UserDto(cart.username)
        val products = cart.products.map { ProductDto(it.name, it.totalPrice, it.quantity, it.links) }
        return EntityModel.of(CartDto(cart.totalPrice, user, products))
    }

    @PostMapping("/carts/{productName}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    fun addCart(@PathVariable productName: String, @RequestBody products: List<ProductDto>) {

    }
}
