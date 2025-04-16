package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class ProductsResource(
    @Autowired private val findProduct: FindProduct,
    @Value("\${app.frontend.base-url}")
    private val frontendBaseUrl: String
) {

    @GetMapping("/products/{id}")
    fun productInfo(@PathVariable id: Long): EntityModel<ProductDto> {
        val localHostAddress = InetAddress.getLocalHost().hostAddress
        val localHostName = InetAddress.getLocalHost().hostName

        // Remote address
        val remoteHostAddress = InetAddress.getLoopbackAddress().hostAddress
        val remoteHostName = InetAddress.getLoopbackAddress().hostName
        println("###################################")
        println("products-service: product id $id requested")
        println("local: $localHostAddress ($localHostName)")
        println("remote: $remoteHostAddress ($remoteHostName)")

        val product = findProduct.by(id)
        return EntityModel.of(ProductDto(frontendBaseUrl, id, product.name, product.reference, product.price))
    }
}
