package com.hateoasatscale.users.domain

import com.hateoasatscale.users.infrastructure.driven.UsersRepository

class UserSearch(private val usersRepository: UsersRepository) : FindUser {
    @Throws(UserNotFound::class)
    override fun by(id: Long): User {
        return usersRepository.findBy(id)
    }
}