package com.hateoasatscale.users.domain

fun interface UsersRepository {
    fun findBy(username: String): User
}