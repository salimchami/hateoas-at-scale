package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.AbstractTests.Companion.Urls.Companion.ADD_PRODUCT
import com.hateoasatscale.cart.AbstractTests.Companion.Urls.Companion.MY_CART
import com.hateoasatscale.cart.UserMock
import com.hateoasatscale.cart.WithJwtMock
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductDto
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProviderProductsDto
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.JsonReader.toRequestedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.`when`
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CartResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.CHARLES)
    fun `should return cart info`() {
        `when`(
            restTemplate.getForObject(
                anyString(),
                eq(ProviderProductsDto::class.java),
            ),
        )
            .thenReturn(ProviderProductsDto(listOf(ProductsFixture.apple, ProductsFixture.pineapple)))

        `when`(usersFeignClient.findBy()).thenReturn(UsersFixture.charlesDarwin)
        val expectedCart = toExpectedJson("cart", "charles-cart")
        http.perform(get(MY_CART))
            .andExpect(content().json(expectedCart, JsonCompareMode.STRICT))
    }

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should add a product to cart`() {
        `when`(
            restTemplate.getForObject(anyString(), eq(ProviderProductDto::class.java)),
        ).thenReturn(ProductsFixture.watermelon)
        `when`(
            restTemplate.getForObject(anyString(), eq(ProviderProductsDto::class.java)),
        ).thenReturn(
            ProviderProductsDto(
                listOf(
                    ProductsFixture.apple,
                    ProductsFixture.pineapple,
                    ProductsFixture.watermelon,
                ),
            ),
        )

        `when`(usersFeignClient.findBy()).thenReturn(UsersFixture.adaLovelace)


        val newCartProducts = toRequestedJson("cart", "product-to-add")
        http.perform(patch(ADD_PRODUCT).content(newCartProducts))
            .andExpect(status().isOk)

        val expectedCart = toExpectedJson("cart", "updated-ada-cart")
        http.perform(get(MY_CART)).andExpect(content().json(expectedCart, JsonCompareMode.STRICT))

    }
}
