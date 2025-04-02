package com.hateoasatscale.users.infrastructure.driven

import com.hateoasatscale.users.domain.User

fun interface UsersRepository {
    fun findBy(id: Long): User
}