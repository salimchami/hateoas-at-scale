package com.hateoasatscale.cart.infrastructure.driven.db

class FakeDbCarts {
    private val carts: MutableMap<DbUser, MutableList<DbProduct>> = mutableMapOf(
        DbUser("ada.lovelace") to mutableListOf(
            DbProduct("apple"),
            DbProduct("pineapple", 2),
        ),
        DbUser("charles.darwin") to mutableListOf(
            DbProduct("apple", 2),
            DbProduct("pineapple"),
        ),
    )

    fun createOrUpdate(user: DbUser, products: List<DbProduct>) {
        carts[user] = products.toMutableList()
    }

    fun findBy(username: String): List<DbProduct> {
        return carts.entries
            .filter { it.key.username == username }
            .flatMap { it.value }
    }
}