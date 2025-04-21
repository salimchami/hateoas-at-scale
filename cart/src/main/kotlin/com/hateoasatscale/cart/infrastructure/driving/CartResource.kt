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
        val user = UserDto(cart.user.username, cart.user.links)
        val products = cart.products.map { ProductDto(it.name, it.price, it.links) }
        val content = CartDto(cart.totalPrice, user, products)
        return EntityModel.of(content)
    }

    @PostMapping("/carts/{username}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    fun addCart(@PathVariable username: String, @RequestBody products: List<ProductDto>) {

    }

    @PutMapping("/carts/{username}")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    fun updateCart(@PathVariable username: String, @RequestBody products: List<ProductDto>) {

    }
}
