package com.hateoasatscale.users.domain

import com.hateoasatscale.users.domain.api.FindUsers
import com.hateoasatscale.users.domain.spi.UsersRepository

class UsersSearch(private val usersRepository: UsersRepository) : FindUsers {
    @Throws(UserNotFound::class)
    override fun all(username: String): List<User> {
        val currentUser = usersRepository.findBy(username)
        if (currentUser.permissions.contains(Permission.READ_ALL_USERS)) {
            return usersRepository.findAll()
        }
        throw NoPermission("No permission to read all users")
    }
}