package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.FindCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/carts")
class CartResource(@Autowired private val findCart: FindCart) {

    @GetMapping
    fun cartInfo(@AuthenticationPrincipal principal: Jwt): EntityModel<CartDto> {
        val username = principal.claims["preferred_username"] as String
        val cart = findCart.by(username)
        val user = UserDto(cart.username)
        val products = cart.products.map { ProductDto(it.name, it.totalPrice, it.quantity, it.links) }
        return EntityModel.of(CartDto(cart.totalPrice, user, products))
    }

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    fun addCart(@AuthenticationPrincipal principal: Jwt, @RequestBody products: List<ProductDto>) {
        val username = principal.claims["preferred_username"] as String

    }
}
