package com.hateoasatscale.products

import com.hateoasatscale.products.infrastructure.driving.CartsFeignClient
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.hateoas.Link
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockitoBean
    protected lateinit var cartsFeignClient: CartsFeignClient

    @BeforeEach
    fun setUp() {
        `when`(cartsFeignClient.startupLinks()).thenReturn(
            listOf(
                Link.of("http://172.25.0.10:8000/carts-service/api/v1/cart/my-cart", "myCart"),
                Link.of("http://172.25.0.10:8000/carts-service/api/v1/cart/add-product", "addProductToCart"),
            ),
        )
    }

    companion object {
        class Urls {
            companion object {
                const val PRODUCT_ORANGE = "/api/v1/products/orange"
                const val ALL_PRODUCTS = "/api/v1/products"
                const val SOME_PRODUCTS = "/api/v1/products/some"
                const val STARTUP = "/api/v1/startup"
            }
        }
    }
}

