package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.FindCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/carts")
class CartResource(@Autowired private val findCart: FindCart) {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun cartInfo(@AuthenticationPrincipal principal: Jwt): EntityModel<CartDto> {
        val username = principal.claims["preferred_username"] as String
        val cart = findCart.by(username)
        val user = UserDto(cart.username)
        val products = cart.products.map { ProductDto(it.name, it.totalPrice, it.quantity, it.links) }
        return EntityModel.of(CartDto(cart.totalPrice, user, products))
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun addToCart(@AuthenticationPrincipal principal: Jwt, @RequestBody products: ProductsToUpdateDto): ResponseEntity<ProductsToUpdateDto> {
        val username = principal.claims["preferred_username"] as String
        return ResponseEntity.ok().build()
    }
}
