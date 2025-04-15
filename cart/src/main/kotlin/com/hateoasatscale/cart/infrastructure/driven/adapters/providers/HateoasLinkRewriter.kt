package com.hateoasatscale.cart.infrastructure.driven.adapters.providers

import org.springframework.hateoas.Link
import org.springframework.hateoas.Links
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

class HateoasLinkRewriter {
    companion object {
        fun rewrite(links: Links, servicePath: String): List<Link> {
            return links.map { originalLink ->
                val originalUri = URI(originalLink.href)

                // Create the new URI with the current host and service path prefix
                val newUri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .replacePath("$servicePath${originalUri.path}")
                    .replaceQuery(originalUri.query)
                    .build()
                    .toUriString()

                Link.of(newUri, originalLink.rel.value())
            }
        }
    }
}