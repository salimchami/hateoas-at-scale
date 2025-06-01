package com.hateoasatscale.products.infrastructure.driving

import jakarta.servlet.http.HttpServletRequest
import org.springframework.hateoas.Link
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
    fun startupLinks(request: HttpServletRequest): ResponseEntity<List<Link>> {
        println("Request URL: ${request.requestURL}")
        println("Request URI: ${request.requestURI}")
        println("Server Name: ${request.serverName}")
        println("Server Port: ${request.serverPort}")
        println("Scheme: ${request.scheme}")
        println("X-Forwarded-Host: ${request.getHeader("X-Forwarded-Host")}")
        println("X-Forwarded-Port: ${request.getHeader("X-Forwarded-Port")}")
        println("X-Forwarded-Proto: ${request.getHeader("X-Forwarded-Proto")}")
        println("X-Forwarded-Prefix: ${request.getHeader("X-Forwarded-Prefix")}")
        return ResponseEntity.ok(StartupDto().links.toList())
    }
}
