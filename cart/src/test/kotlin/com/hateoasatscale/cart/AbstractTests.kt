package com.hateoasatscale.cart

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsProvider
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractTests {

    @LocalServerPort
    protected var port: Int = 0

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    protected val baseUrl: String get() = "http://localhost:$port"

    @MockitoBean
    protected lateinit var usersProvider: UsersProvider;

    @MockitoBean
    protected lateinit var productsProvider: ProductsProvider;
}
