package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.AbstractTests
import com.hateoasatscale.users.AbstractTests.Companion.Urls.Companion.ALL_USERS
import com.hateoasatscale.users.AbstractTests.Companion.Urls.Companion.USER_INFO
import com.hateoasatscale.users.UserMock
import com.hateoasatscale.users.WithJwtMock
import com.hateoasatscale.users.infrastructure.hateoas.WorkflowLinks
import com.hateoasatscale.users.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.hateoas.Link
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserResourceTest : AbstractTests() {

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should find user info with links from username`() {
        `when`(cartsFeignClient.startupLinks()).thenReturn(
            listOf(
                Link.of("http://172.25.0.10:8000/carts-service/api/v1/cart/my-cart", WorkflowLinks.MY_CART),
                Link.of(
                    "http://172.25.0.10:8000/carts-service/api/v1/cart/add-product",
                    WorkflowLinks.ADD_PRODUCT_TO_CART,
                ),
            ),
        )
        `when`(productsFeignClient.startupLinks()).thenReturn(
            listOf(
                Link.of("http://172.25.0.10:8000/products-service/api/v1/products", WorkflowLinks.ALL_PRODUCTS),
            ),
        )
        val expectedUser = toExpectedJson("users/user", "user-ada")
        val result = mockMvc.perform(get(USER_INFO))
            .andExpect(status().isOk)
            .andReturn()
        assertThat(result.response.contentAsString).isEqualTo(expectedUser)
    }

    @Test
    @WithJwtMock(UserMock.ADA)
    fun `should find all user`() {
        val expectedUser = toExpectedJson("users", "all")
        val result = mockMvc.perform(get(ALL_USERS))
            .andExpect { status().isOk }
            .andReturn()
        assertThat(result.response.contentAsString).isEqualTo(expectedUser)
    }
}
