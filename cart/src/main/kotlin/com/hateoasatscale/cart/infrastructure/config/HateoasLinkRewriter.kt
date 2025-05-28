package com.hateoasatscale.cart.infrastructure.config

import org.springframework.hateoas.Link
import org.springframework.hateoas.Links
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

class HateoasLinkRewriter {
    companion object {
        fun rewrite(links: Links, servicePath: String): List<Link> {
            return links.map { originalLink ->
                val originalUri = URI(originalLink.href)
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
