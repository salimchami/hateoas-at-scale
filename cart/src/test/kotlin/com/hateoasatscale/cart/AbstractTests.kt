package com.hateoasatscale.cart

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests {

    @MockitoBean
    protected lateinit var usersFeignClient: UsersFeignClient

    @MockitoBean
    protected lateinit var productsProvider: ProductsFeignClient

    @Autowired
    protected lateinit var mockMvc: MockMvc

    companion object {
        class Urls {
            companion object {
                const val CARTS = "/api/v1/carts"
            }
        }
    }
}
