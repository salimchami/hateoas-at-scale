package com.hateoasatscale.users.domain.api

import com.hateoasatscale.users.domain.User

fun interface FindUsers {
    fun all(username: String): List<User>
}