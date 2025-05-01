package com.hateoasatscale.users.domain

import com.hateoasatscale.roles.Role

class User(
    private val role: Role,
    val username: String,
    val firstname: String,
    val lastname: String,
    var permissions: List<Permission> = listOf()
) {
    init {
        this.permissions = when (role) {
            Role.ADMIN -> Permission.entries
            Role.CUSTOMER -> listOf<Permission>(Permission.READ_OWN_USER, Permission.READ_CART, Permission.READ_PRODUCTS)
            Role.ANONYMOUS -> listOf<Permission>()
        }
    }
}
