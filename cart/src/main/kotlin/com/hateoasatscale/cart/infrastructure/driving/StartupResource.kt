package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.infrastructure.config.HateoasLinkRewriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.hateoas.Link
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/startup")
class StartupResource(@Value("\${spring.application.name}") private val serviceName: String) {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    fun startupLinks(): ResponseEntity<List<Link>> {
        return ResponseEntity.ok(HateoasLinkRewriter.rewrite(StartupDto().links, serviceName).toList())
    }
}
