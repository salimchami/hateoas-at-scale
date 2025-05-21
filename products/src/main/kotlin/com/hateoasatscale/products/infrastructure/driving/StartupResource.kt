package com.hateoasatscale.products.infrastructure.driving

import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/startup")
class StartupResource() {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun startupLinks(): ResponseEntity<List<Link>> {
        return ResponseEntity.ok(
            listOf(
                WebMvcLinkBuilder.linkTo(ProductsResource::class.java.methods.first { it.name == "findAll" })
                    .withRel { "products" },
            ),
        )
    }
}
