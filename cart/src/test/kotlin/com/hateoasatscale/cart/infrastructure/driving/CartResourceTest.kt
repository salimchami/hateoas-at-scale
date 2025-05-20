package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.AbstractTests.Companion.Urls.Companion.CARTS
import com.hateoasatscale.cart.UserMock
import com.hateoasatscale.cart.WithJwtMock
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.JsonReader.toRequestedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CartResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should return cart info with links`() {
        `when`(usersFeignClient.findBy()).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyString())).thenReturn(ProductsFixture.apple)
            .thenReturn(ProductsFixture.pineapple)
        val expectedCart = toExpectedJson("cart", "ada-cart")
        http.perform(get(CARTS))
            .andExpect(content().json(expectedCart, JsonCompareMode.STRICT))
    }

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should update a user cart`() {
        val productsToUpdate = toRequestedJson("cart", "added-products-to-ada-cart")
        val expectedCart = toExpectedJson("cart", "updated-ada-cart")
        http.perform(patch("/api/v1/carts").content(productsToUpdate))
            .andExpect(status().isOk)

        `when`(usersFeignClient.findBy()).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyString())).thenReturn(ProductsFixture.apple)
            .thenReturn(ProductsFixture.pineapple)
        http.perform(get(CARTS)).andExpect(content().json(expectedCart, JsonCompareMode.STRICT))

    }

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should run test`() {
        http.perform(get("$CARTS/test"))
            .andExpect(content().string("it works!"))
    }
}
