package com.hateoasatscale.cart.infrastructure.driving

import com.hateoasatscale.cart.AbstractTests
import com.hateoasatscale.cart.utils.JsonReader.toExpectedJson
import com.hateoasatscale.cart.utils.ProductsFixture
import com.hateoasatscale.cart.utils.UsersFixture
import org.junit.jupiter.api.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class FindCartResourceTest : AbstractTests() {

    @Test
    fun `should return cart info with links`() {
        `when`(usersFeignClient.findBy(anyString())).thenReturn(UsersFixture.adaLovelace)
        `when`(productsProvider.findBy(anyString())).thenReturn(ProductsFixture.apple)
            .thenReturn(ProductsFixture.pineapple)
        val expectedProduct =
            toExpectedJson("cart", "cart-ada-orange").replace(
                "{{service-url}}", baseUrl
            )
        endPointCaller.perform(get("/carts/ada.lovelace"))
            .andExpect(content().json(expectedProduct, JsonCompareMode.STRICT))
    }

    @Test
    fun `should add product to a user cart`() {
//        val request: HttpEntity<List<ProductDto>> = HttpEntity(
//            listOf(
//                ProductDto(
//                    "apple",
//                    BigDecimal(10), emptyList()
//                ), ProductDto("orange", BigDecimal(15), emptyList())
//            )
//        )
//        val entity = restTemplate.postForObject<String>("$baseUrl/carts/ada.lovelace")

    }
}