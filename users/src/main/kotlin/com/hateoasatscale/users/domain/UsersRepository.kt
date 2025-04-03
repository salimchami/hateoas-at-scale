package com.hateoasatscale.users.domain

fun interface UsersRepository {
    fun findBy(id: Long): User
}