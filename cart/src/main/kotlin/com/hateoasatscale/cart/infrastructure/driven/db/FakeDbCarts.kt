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
        val currentProducts = carts.getOrPut(user) { mutableListOf() }
        products.forEach { productToAdd ->
            val existingProductIndex = currentProducts.indexOfFirst { it.name == productToAdd.name }
            if (existingProductIndex == -1) {
                currentProducts.add(productToAdd)
            } else {
                val existingProduct = currentProducts[existingProductIndex]
                val updatedQuantity = existingProduct.quantity + productToAdd.quantity
                currentProducts[existingProductIndex] = DbProduct(existingProduct.name, updatedQuantity)
            }
        }
    }


    fun findBy(username: String): List<DbProduct> {
        return carts.entries
            .filter { it.key.username == username }
            .flatMap { it.value }
    }
}