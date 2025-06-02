package com.hateoasatscale.products.infrastructure.driving

import com.hateoasatscale.products.AbstractTests
import com.hateoasatscale.products.AbstractTests.Companion.Urls.Companion.STARTUP
import com.hateoasatscale.products.UserMock
import com.hateoasatscale.products.WithJwtMock
import com.hateoasatscale.products.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class StartupResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.MARTIN)
    fun `should return product info`() {
        val expectedProduct = toExpectedJson("startup", "startup")
        val product = mockMvc.perform(get(STARTUP)).andReturn().response.contentAsString
        assertThat(product).isEqualTo(expectedProduct)
    }
}
