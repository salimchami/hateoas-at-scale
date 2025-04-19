package com.hateoasatscale.users.domain

fun interface FindUser {
    fun by(username: String): User
}
