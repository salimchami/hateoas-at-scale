package com.hateoasatscale.products.controllers.products

import com.hateoasatscale.products.config.UrlServiceResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/products")
class ProductsController(@Autowired private val serviceResolver: UrlServiceResolver, @Value("\${spring.application.name}") private val appName: String) {

    @GetMapping("/{id}")
    fun userInfo(@PathVariable id: Long): HttpEntity<Product> {
        val usersAppUrl = this.serviceResolver.getServiceUrl(appName)
        return HttpEntity<Product>(Product(usersAppUrl, id, "head-quite-comfort", "45654FFDSV", BigDecimal(123.45)))
    }
}
