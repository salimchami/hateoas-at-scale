package com.hateoasatscale.cart.infrastructure.driven.db

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeDbCartsTest {

    lateinit var sut: FakeDbCarts
    private val adalovelace = "ada.lovelace"
    private val strawberry: String = "strawberry"
    private val apple = "apple"
    private val pineapple = "pineapple"

    @BeforeEach
    fun setUp() {
        sut = FakeDbCarts()
    }

    @Test
    fun `should add a product for a user`() {
        sut.updateProduct(adalovelace, strawberry, 2)
        assertThat(sut.findBy(adalovelace)).isEqualTo(
            listOf(
                DbProduct(apple),
                DbProduct(pineapple, 2),
                DbProduct(strawberry, 2)
            )
        )
    }

    @Test
    fun `should find cart for a user`() {
        val user = DbUser(adalovelace)
        val adaProducts = listOf(DbProduct(apple), DbProduct(pineapple, 2))
        val products: List<DbProduct> = sut.findBy(user.username)
        assertThat(products).isEqualTo(adaProducts)
    }
}
