package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.User
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.UsersRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.HateoasLinkRewriter
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import org.springframework.stereotype.Component

@Component
class UsersAdapter(private val usersFeignClient: UsersFeignClient) : UsersRepository {
    override fun findBy(id: Long): User {
        val user = usersFeignClient.findBy(id)
        val links = HateoasLinkRewriter.rewrite(user.links, "users-service")
        return User(user.firstname, user.lastname, links.map { Link(it.href, it.rel.value()) })
    }
}