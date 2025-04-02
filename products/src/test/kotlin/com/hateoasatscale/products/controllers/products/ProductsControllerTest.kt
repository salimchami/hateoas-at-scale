package com.hateoasatscale.products.controllers.products

import com.hateoasatscale.products.AbstractTests
import com.hateoasatscale.products.utils.JsonReader.strip
import com.hateoasatscale.products.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class ProductsControllerTest : AbstractTests() {

    @Test
    fun `should return product info with links`() {
        val expectedUser = toExpectedJson("products/product", "product-1")
        val entity = restTemplate.getForEntity<String>("/products/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }
}