package com.hateoasatscale.users.domain

class UserSearch(private val usersRepository: UsersRepository) : FindUser {
    @Throws(UserNotFound::class)
    override fun by(id: Long): User {
        return usersRepository.findBy(id)
    }
}