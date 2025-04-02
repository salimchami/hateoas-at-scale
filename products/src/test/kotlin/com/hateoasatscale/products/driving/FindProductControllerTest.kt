package com.hateoasatscale.products.driving

import com.hateoasatscale.products.AbstractTests
import com.hateoasatscale.users.utils.JsonReader.strip
import com.hateoasatscale.users.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class FindProductControllerTest : AbstractTests() {

    @Test
    fun `should return product info with links`() {
        val expectedProduct =
            toExpectedJson("products/product", "product-orange").replace("{{products-service-url}}", "localhost:${productsServerPort}")
        val entity = restTemplate.getForEntity<String>("/products/4")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedProduct)
    }
}