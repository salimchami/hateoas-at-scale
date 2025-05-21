package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.AddToCart
import com.hateoasatscale.cart.domain.api.FindCart
import com.hateoasatscale.cart.domain.entities.add.ProductToAdd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cart")
class CartResource(@Autowired private val findCart: FindCart,
    @Autowired private val addToCart: AddToCart
) {

    @GetMapping("my-cart")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun myCart(@AuthenticationPrincipal principal: Jwt): EntityModel<CartDto> {
        val username = principal.claims["preferred_username"] as String
        val cart = findCart.by(username)
        val user = UserDto(cart.username)
        val products = cart.products.map { ProductDto(it.name, it.totalPrice, it.quantity, it.links) }
        return EntityModel.of(CartDto(cart.totalPrice, user, products))
    }

    @PatchMapping("add-product")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun addToCart(@AuthenticationPrincipal principal: Jwt, @RequestBody productToAdd: ProductToUpdateDto): ResponseEntity<Unit> {
        val username = principal.claims["preferred_username"] as String
        addToCart.theProduct(username, ProductToAdd(productToAdd.name, productToAdd.quantity))
        return ResponseEntity.ok().build()
    }
}
