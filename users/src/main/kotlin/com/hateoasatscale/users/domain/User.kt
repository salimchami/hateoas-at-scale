package com.hateoasatscale.users.domain

class User(
    private val role: Role,
    val username: String,
    val firstname: String,
    val lastname: String,
    var permissions: List<Permission> = listOf()
) {
    init {
        this.permissions = when (role) {
            Role.ROLE_ADMIN -> Permission.entries
            Role.ROLE_CUSTOMER -> listOf<Permission>(Permission.READ_OWN_USER, Permission.READ_CART, Permission.READ_PRODUCTS)
            Role.ROLE_ANONYMOUS -> listOf<Permission>()
        }
    }
}
