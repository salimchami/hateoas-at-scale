package com.hateoasatscale.cart.controllers.cart

import com.hateoasatscale.cart.config.UrlServiceResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/cart")
class CartController(
    @Autowired private val serviceResolver: UrlServiceResolver,
    @Value("\${spring.application.name}") private val appName: String
) {

    @GetMapping("/{userId}")
    fun userInfo(@PathVariable userId: Long): HttpEntity<Cart> {
        val usersAppUrl = this.serviceResolver.getServiceUrl(appName)
        return HttpEntity<Cart>(Cart(usersAppUrl, userId, listOf(), BigDecimal(400.45)))
    }
}
