package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import com.hateoasatscale.products.domain.FindProducts
import org.springframework.beans.factory.annotation.Value
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsResource(
    private val findProduct: FindProduct,
    private val findProducts: FindProducts,
    @Value("\${application.network.gateway-url}") private val gatewayUrl: String,
    @Value("\${application.network.services.carts.name}") private val cartsServiceName: String,
    @Value("\${application.network.services.carts.endpoints.add}") private val addProductToCartEndpoint: String,

    ) {

    @GetMapping("/products/{name}")
    fun find(@PathVariable name: String): EntityModel<ProductDto> {
        val product = findProduct.by(name)
        return EntityModel.of(
            ProductDto(
                gatewayUrl,
                cartsServiceName,
                addProductToCartEndpoint,
                product.name,
                product.reference,
                product.price,
            ),
        )
    }

    @GetMapping("/products")
    fun findAll(): EntityModel<ProductsDto> {
        val products = findProducts.all()
        return EntityModel.of(
            ProductsDto(
                products.map { product ->
                    ProductDto(
                        gatewayUrl,
                        cartsServiceName,
                        addProductToCartEndpoint,
                        product.name,
                        product.reference,
                        product.price,
                    )
                },
            ),
        )
    }
}
