package com.hateoasatscale.users.domain

import com.hateoasatscale.users.domain.api.FindUser
import com.hateoasatscale.users.domain.spi.UsersRepository

class UserSearch(private val usersRepository: UsersRepository) : FindUser {
    @Throws(UserNotFound::class)
    override fun by(username: String): User {
        return usersRepository.findBy(username)
    }
}