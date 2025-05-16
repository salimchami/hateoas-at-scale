package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.AbstractTests.Companion.Urls.Companion.CARTS
import com.hateoasatscale.cart.UserMock
import com.hateoasatscale.cart.WithJwtMock
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class CartResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should return cart info with links`() {
        `when`(usersFeignClient.findBy()).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyString())).thenReturn(ProductsFixture.apple)
            .thenReturn(ProductsFixture.pineapple)
        val expectedProduct =
            toExpectedJson("cart", "cart-ada-orange")
        mockMvc.perform(get(CARTS))
            .andExpect(content().json(expectedProduct, JsonCompareMode.STRICT))
    }

    @Test
    @WithJwtMock(UserMock.KAREN)
    fun `should add product to a user cart`() {
        mockMvc.perform(post("/carts"))
    }
}
