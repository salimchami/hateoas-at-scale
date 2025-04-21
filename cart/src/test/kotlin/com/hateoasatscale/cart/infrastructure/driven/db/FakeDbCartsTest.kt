package com.hateoasatscale.cart.infrastructure.driven.db

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.ProviderUserDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class FakeDbCartsTest {

    @Test
    fun `should add products for a user`() {
        val sut = FakeDbCarts()
        val user = ProviderUserDto("ada.lovelace")
        val products = listOf(ProviderProductDto("apple", BigDecimal(10)))
        sut.add(user, products)
        assertThat(sut.myMap).isEqualTo(mapOf(user to products))
    }

    @Test
    fun `should find cart for a user`() {
        val sut = FakeDbCarts()
        val user1 = ProviderUserDto("ada.lovelace")
        val user2 = ProviderUserDto("martin.lutherking")
        val products1 = listOf(ProviderProductDto("apple", BigDecimal(10)))
        val products2 =
            listOf(ProviderProductDto("apple", BigDecimal(10)), ProviderProductDto("pineapple", BigDecimal(15)))
        sut.add(user1, products1)
        sut.add(user2, products2)
        val products: List<ProviderProductDto> = sut.findBy(user2.username)
        assertThat(products).isEqualTo(products2)
    }
}