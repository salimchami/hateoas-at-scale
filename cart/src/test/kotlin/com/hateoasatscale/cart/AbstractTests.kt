package com.hateoasatscale.cart

import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.products.ProductsFeignClient
import com.hateoasatscale.cart.infrastructure.driven.adapters.providers.users.UsersFeignClient
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
abstract class AbstractTests: InitializingBean {

    protected lateinit var http: EndpointCaller

    @MockitoBean
    protected lateinit var usersFeignClient: UsersFeignClient

    @MockitoBean
    protected lateinit var productsProvider: ProductsFeignClient

    @Autowired
    private lateinit var mockMvc: MockMvc

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

