package com.hateoasatscale.products

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    companion object {
        class Urls {
            companion object {
                const val PRODUCT_ORANGE = "/api/v1/products/orange"
                const val ALL_PRODUCTS = "/api/v1/products"
            }
        }
    }
}

