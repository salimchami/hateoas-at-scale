package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.EntityModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductsResourceImpl(@Autowired private val findProduct: FindProduct) : ProductsResource {

    @GetMapping("/products/{id}")
    override fun userInfo(@PathVariable id: Long): EntityModel<ProductDto> {
        val product = findProduct.by(id)
        return EntityModel.of(ProductDto(id, product.name, product.reference, product.price))
    }
}
