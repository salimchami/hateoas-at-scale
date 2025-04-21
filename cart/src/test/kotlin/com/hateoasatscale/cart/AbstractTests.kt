package com.hateoasatscale.cart

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import com.hateoasatscale.cart.utils.EndPointCaller
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests: InitializingBean {

    @LocalServerPort
    protected var port: Int = 0

    protected lateinit var endPointCaller: EndPointCaller

    override fun afterPropertiesSet() {
        endPointCaller = EndPointCaller(mockMvc)
    }

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    protected val baseUrl: String get() = "http://localhost:$port"

    @MockitoBean
    protected lateinit var usersFeignClient: UsersFeignClient

    @MockitoBean
    protected lateinit var productsProvider: ProductsFeignClient
}
