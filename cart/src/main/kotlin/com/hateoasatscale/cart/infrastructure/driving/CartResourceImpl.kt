package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.FindCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CartResourceImpl(@Autowired private val findCart: FindCart) : CartResource {

    @GetMapping("/cart/{id}")
    override fun userInfo(@PathVariable id: Long): EntityModel<CartDto> {
        val cart = findCart.by(id)
        val user = UserDto(cart.user.firstname, cart.user.lastname, cart.user.links)
        val products = cart.products.map { ProductDto(it.name, it.reference, it.price, it.links) }
        return EntityModel.of(CartDto(cart.id, cart.totalPrice, user, products))
    }
}
