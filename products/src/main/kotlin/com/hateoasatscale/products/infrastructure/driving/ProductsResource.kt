package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import com.hateoasatscale.products.domain.FindProducts
import org.springframework.hateoas.EntityModel
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductsResource(
    private val findProduct: FindProduct,
    private val findProducts: FindProducts,
    private val cartsFeignClient: CartsFeignClient,
) {

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping("/{name}")
    fun find(@PathVariable name: String): EntityModel<ProductDto> {
        val product = findProduct.by(name)
        val cartsStartupLinks = cartsFeignClient.startupLinks()
        return EntityModel.of(
            ProductDto(
                cartsStartupLinks,
                product.name,
                product.reference,
                product.price,
            ),
        )
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping()
    fun findAll(): EntityModel<ProductsDto> {
        val products = findProducts.all()
        val cartsStartupLinks = cartsFeignClient.startupLinks()
        return EntityModel.of(
            ProductsDto(
                products.map { product ->
                    ProductDto(
                        cartsStartupLinks,
                        product.name,
                        product.reference,
                        product.price,
                    )
                },
            ),
        )
    }
}
