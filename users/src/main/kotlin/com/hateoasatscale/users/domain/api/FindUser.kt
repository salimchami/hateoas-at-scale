package com.hateoasatscale.users.domain.api

import com.hateoasatscale.users.domain.User

fun interface FindUser {
    fun by(username: String): User
}