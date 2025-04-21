package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import com.hateoasatscale.products.domain.FindProducts
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsResource(
    private val findProduct: FindProduct,
    private val findProducts: FindProducts
) {

    @GetMapping("/products/{name}")
    fun find(@PathVariable name: String): EntityModel<ProductDto> {
        val product = findProduct.by(name)
        return EntityModel.of(ProductDto(product.name, product.reference, product.price))
    }

    @GetMapping("/products")
    fun findAll(): EntityModel<ProductsDto> {
        val products = findProducts.all()
        return EntityModel.of(ProductsDto(products.map { product ->
            ProductDto(
                product.name,
                product.reference,
                product.price
            )
        }))
    }
}
