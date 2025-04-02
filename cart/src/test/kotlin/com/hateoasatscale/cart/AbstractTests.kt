package com.hateoasatscale.cart

import com.hateoasatscale.cart.config.UrlServiceResolver
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractTests {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @MockitoBean
    protected lateinit var urlServiceResolver: UrlServiceResolver

    @BeforeEach
    fun beforeEach() {
        `when`(urlServiceResolver.getServiceUrl("cart")).thenReturn(
            "http://172.20.0.4:32592"
        )
    }
}
