package com.hateoasatscale.cart.infrastructure.driven.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeDbCartsTest {

    lateinit var sut: FakeDbCarts
    private val adalovelace = "ada.lovelace"
    private val strawberry: String = "strawberry"
    private val apple = "apple"

    @BeforeEach
    fun setUp() {
        sut = FakeDbCarts()
    }

    @Test
    fun `should add products for a user`() {
        sut.createOrUpdate(DbUser(adalovelace), listOf(DbProduct(apple, 15), DbProduct(strawberry)))
        assertThat(sut.findBy(adalovelace)).isEqualTo(
            listOf(
                DbProduct(apple, 15),
                DbProduct(strawberry)
            )
        )
    }

    @Test
    fun `should find cart for a user`() {
        val user = DbUser(adalovelace)
        val adaProducts = listOf(DbProduct(apple))
        sut.createOrUpdate(user, adaProducts)
        val products: List<DbProduct> = sut.findBy(user.username)
        assertThat(products).isEqualTo(adaProducts)
    }
}