package com.hateoasatscale.users.domain

class UserSearch(private val usersRepository: UsersRepository) : FindUser {
    @Throws(UserNotFound::class)
    override fun by(username: String): User {
        return usersRepository.findBy(username)
    }
}