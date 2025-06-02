package com.hateoasatscale.cart

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import com.hateoasatscale.cart.utils.ProductsFixture
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
abstract class AbstractTests : InitializingBean {

    protected lateinit var http: EndpointCaller

    @MockitoBean
    protected lateinit var usersFeignClient: UsersFeignClient

    @MockitoBean
    protected lateinit var productsProvider: ProductsFeignClient

     @MockitoBean
    protected lateinit var restTemplate: RestTemplate


    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        `when`(productsProvider.startup()).thenReturn(ProductsFixture.startupLinks)
    }

    override fun afterPropertiesSet() {
        this.http = EndpointCaller(mockMvc)
    }

    companion object {
        class Urls {
            companion object {
                const val MY_CART: String = "/api/v1/cart/my-cart"
                const val ADD_PRODUCT: String = "/api/v1/cart/add-product"
            }
        }
    }
}

