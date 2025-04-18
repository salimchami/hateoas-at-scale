package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.domain.api.FindCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class CartResource(@Autowired private val findCart: FindCart) {

    @GetMapping("/carts/{id}")
    fun cartInfo(@PathVariable id: Long): EntityModel<CartDto> {
        val localHostAddress = InetAddress.getLocalHost().hostAddress
        val localHostName = InetAddress.getLocalHost().hostName

        // Remote address
        val remoteHostAddress = InetAddress.getLoopbackAddress().hostAddress
        val remoteHostName = InetAddress.getLoopbackAddress().hostName
        println("###################################")
        println("carts-service: cart id $id requested")
        println("local: $localHostAddress ($localHostName)")
        println("remote: $remoteHostAddress ($remoteHostName)")
        val cart = findCart.by(id)
        val user = UserDto(cart.user.firstname, cart.user.lastname, cart.user.links)
        val products = cart.products.map { ProductDto(it.name, it.reference, it.price, it.links) }
        val content = CartDto(cart.id, cart.totalPrice, user, products)
        return EntityModel.of(content)
    }
}
