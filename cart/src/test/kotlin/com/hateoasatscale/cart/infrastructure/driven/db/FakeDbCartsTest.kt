package com.hateoasatscale.cart.infrastructure.driven.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeDbCartsTest {

    lateinit var sut: FakeDbCarts
    private val adalovelace = "ada.lovelace"
    private val martinlutherking = "martin.lutherking"
    private val strawberry: String = "strawberry"
    private val apple = "apple"
    private val pineapple = "pineapple"

    @BeforeEach
    fun setUp() {
        sut = FakeDbCarts()
    }

    @Test
    fun `should add products for a user`() {
        sut.createOrUpdate(DbUser(adalovelace), listOf(DbProduct(apple), DbProduct(strawberry)))
        assertThat(sut.findBy(adalovelace)).isEqualTo(
            listOf(
                DbProduct(apple, 2),
                DbProduct(pineapple, 2),
                DbProduct(strawberry)
            )
        )
    }

    @Test
    fun `should find cart for a user`() {
        val user1 = DbUser(adalovelace)
        val user2 = DbUser(martinlutherking)
        val products1 = listOf(DbProduct(apple))
        val products2 =
            listOf(DbProduct(apple), DbProduct(pineapple))
        sut.createOrUpdate(user1, products1)
        sut.createOrUpdate(user2, products2)
        val products: List<DbProduct> = sut.findBy(user2.username)
        assertThat(products).isEqualTo(products2)
    }
}