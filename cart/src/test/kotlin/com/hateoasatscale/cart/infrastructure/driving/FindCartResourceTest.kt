package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.utils.JsonReader.strip
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class FindCartResourceTest : AbstractTests() {

    @Test
    fun `should return cart info with links`() {
        `when`(usersFeignClient.findBy(anyLong())).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyLong())).thenReturn(ProductsFixture.apple).thenReturn(ProductsFixture.orange)
        val expectedProduct =
            toExpectedJson("cart", "cart-ada-orange").replace(
                "{{service-url}}", baseUrl
            )
        val entity = restTemplate.getForEntity<String>("$baseUrl/carts/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedProduct)
    }
}