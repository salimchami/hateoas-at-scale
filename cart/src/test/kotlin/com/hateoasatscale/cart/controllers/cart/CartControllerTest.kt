package com.hateoasatscale.cart.controllers.cart

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.utils.JsonReader.strip
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class CartControllerTest : AbstractTests() {

    @Test
    fun `should return user info with links`() {
        val expectedCart = toExpectedJson("cart", "cart-1")
        val entity = restTemplate.getForEntity<String>("/cart/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedCart)
    }
}