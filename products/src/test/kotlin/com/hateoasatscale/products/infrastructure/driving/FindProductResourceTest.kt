package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.AbstractTests
import com.hateoasatscale.products.utils.JsonReader.strip
import com.hateoasatscale.products.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class FindProductResourceTest : AbstractTests() {

    @Test
    fun `should return product info with links`() {
        val expectedProduct =
            toExpectedJson("products/product", "product-orange").replace(
                "{{products-service-url}}", baseUrl
            )
        val entity = restTemplate.getForEntity<String>("$baseUrl/products/4")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedProduct)
    }

    @Test
    fun `should return products list`() {
        val expectedProduct =
            toExpectedJson("products/list", "products-list").replace(
                "{{products-service-url}}", baseUrl
            )
        val entity = restTemplate.getForEntity<String>("$baseUrl/products")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedProduct)
    }
}