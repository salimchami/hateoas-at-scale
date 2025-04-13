package com.hateoasatscale.cart.infrastructure.driven.adapters

import com.hateoasatscale.cart.domain.entities.User
import com.hateoasatscale.cart.domain.share.network.Link
import com.hateoasatscale.cart.domain.spi.UsersRepository
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersServiceClient
import org.springframework.stereotype.Component

@Component
class UsersAdapter(private val usersServiceClient: UsersServiceClient) : UsersRepository {
    override fun findBy(id: Long): User {
        val user = usersServiceClient.findBy(id)
        return User(user.firstname, user.lastname, user.links.map { Link(it.href, it.rel.value()) })
    }
}