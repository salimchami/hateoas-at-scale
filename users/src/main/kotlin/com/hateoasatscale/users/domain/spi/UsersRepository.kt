package com.hateoasatscale.users.domain.spi

import com.hateoasatscale.users.domain.User

interface UsersRepository {
    fun findBy(username: String): User
    fun findAll(): List<User>
}