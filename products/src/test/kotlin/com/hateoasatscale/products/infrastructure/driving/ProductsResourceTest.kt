package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.AbstractTests
import com.hateoasatscale.products.AbstractTests.Companion.Urls.Companion.ALL_PRODUCTS
import com.hateoasatscale.products.AbstractTests.Companion.Urls.Companion.PRODUCT_ORANGE
import com.hateoasatscale.products.UserMock
import com.hateoasatscale.products.WithJwtMock
import com.hateoasatscale.products.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class ProductsResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.MARTIN)
    fun `should return product info with links`() {
        val expectedProduct = toExpectedJson("products/product", "product-orange")
        val product = mockMvc.perform(get(PRODUCT_ORANGE)).andReturn().response.contentAsString
        assertThat(product).isEqualTo(expectedProduct)
    }

    @Test
    @WithJwtMock(UserMock.RICHARD)
    fun `should return products list`() {
        val expectedProduct = toExpectedJson("products/list", "products-list")
        val products = mockMvc.perform(get(ALL_PRODUCTS)).andReturn().response.contentAsString
        assertThat(products).isEqualTo(expectedProduct)
    }

    @Test
    @WithJwtMock(UserMock.RICHARD)
    fun `should return products list by names`() {
        val expectedProduct = toExpectedJson("products/list", "products-list-by-names")
        val products = mockMvc.perform(
            get(ALL_PRODUCTS)
                .param("name", "apple")
                .param("name", "pineapple"),
        )
            .andReturn().response.contentAsString
        assertThat(products).isEqualTo(expectedProduct)
    }
}
