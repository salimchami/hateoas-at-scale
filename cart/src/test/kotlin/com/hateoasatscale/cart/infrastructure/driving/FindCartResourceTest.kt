package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.utils.JsonReader.strip
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import java.math.BigDecimal

class FindCartResourceTest : AbstractTests() {

    @Test
    fun `should return cart info with links`() {
        `when`(usersFeignClient.findBy(anyString())).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyString())).thenReturn(ProductsFixture.apple)
            .thenReturn(ProductsFixture.orange)
        val expectedProduct =
            toExpectedJson("cart", "cart-ada-orange").replace(
                "{{service-url}}", baseUrl
            )
        val entity = restTemplate.getForEntity<String>("$baseUrl/carts/ada.lovelace")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedProduct)
    }

    @Test
    fun `should add product to a user cart`() {
        val request: HttpEntity<List<ProductDto>> = HttpEntity(listOf<ProductDto>(ProductDto("apple",
            BigDecimal(10), emptyList()), ProductDto("orange", BigDecimal(15), emptyList())))
        val entity = restTemplate.postForObject<String>("$baseUrl/carts/ada.lovelace")

    }
}