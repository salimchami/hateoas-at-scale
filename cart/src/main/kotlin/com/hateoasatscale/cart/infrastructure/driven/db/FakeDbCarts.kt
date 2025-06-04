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

    fun findBy(username: String): List<DbProduct> {
        return carts.entries
            .filter { it.key.username == username }
            .flatMap { it.value }
    }

    fun updateProduct(username: String, productName: String, quantityToAdd: Int) {
        carts.getOrPut(DbUser(username)) { mutableListOf() }
            .updateOrAddProduct(productName, quantityToAdd)
    }

    private fun MutableList<DbProduct>.updateOrAddProduct(productName: String, quantityToAdd: Int) {
        val existingIndex = indexOfFirst { it.name == productName }
        if (existingIndex >= 0) {
            this[existingIndex] = this[existingIndex].copy(quantity = this[existingIndex].quantity + quantityToAdd)
        } else {
            add(DbProduct(productName, quantityToAdd))
        }
    }}
