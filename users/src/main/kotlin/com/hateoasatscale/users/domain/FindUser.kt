package com.hateoasatscale.users.domain

fun interface FindUser {
    fun by(id: Long): User
}
