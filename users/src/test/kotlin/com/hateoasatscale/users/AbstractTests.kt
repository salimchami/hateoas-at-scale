package com.hateoasatscale.users

import com.hateoasatscale.users.infrastructure.driving.CartsFeignClient
import com.hateoasatscale.users.infrastructure.driving.ProductsFeignClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc
    @MockitoBean
    protected lateinit var cartsFeignClient: CartsFeignClient
    @MockitoBean
    protected lateinit var productsFeignClient: ProductsFeignClient

    companion object {
        class Urls {
            companion object {
                const val USER_INFO = "/api/v1/users/user-info"
                const val ALL_USERS = "/api/v1/users/all"
            }
        }
    }
}

