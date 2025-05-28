package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.domain.FindProduct
import com.hateoasatscale.products.domain.FindProducts
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/products")
class ProductsResource(
    private val findProduct: FindProduct,
    private val findProducts: FindProducts,
    private val cartsFeignClient: CartsFeignClient,
) {

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    @GetMapping("/{name}")
    fun find(@RequestHeader headers: Map<String, String>, @PathVariable name: String): ResponseEntity<ProductDto> {
        val product = findProduct.by(name)
        val feignClientHeaders = headers.filter {
            it.key.lowercase() == "authorization" || it.key.lowercase()
                .startsWith("x-forwarded-") || it.key.lowercase() == "x-service-path"
        }
        val cartsStartupLinks = cartsFeignClient.startupLinks(
            feignClientHeaders + mapOf(

                "X-Forwarded-Prefix" to "/carts-service",
                "X-Forwarded-Host" to "localhost",
                "X-Forwarded-Port" to "8020",
                "X-Forwarded-Proto" to "http",
            ),
        )
        return ResponseEntity.ok(
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
    fun findAll(@RequestHeader headers: Map<String, String>): ResponseEntity<ProductsDto> {
        val products = findProducts.all()
        val feignClientHeaders = headers.filter {
            it.key.lowercase() == "authorization" || it.key.lowercase()
                .startsWith("x-forwarded-") || it.key.lowercase() == "x-service-path"
        }
        val cartsStartupLinks = cartsFeignClient.startupLinks(
            feignClientHeaders + mapOf(

                "X-Forwarded-Prefix" to "/carts-service",
                "X-Forwarded-Host" to "localhost",
                "X-Forwarded-Port" to "8020",
                "X-Forwarded-Proto" to "http",
            ),
        )

        return ResponseEntity.ok(
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
