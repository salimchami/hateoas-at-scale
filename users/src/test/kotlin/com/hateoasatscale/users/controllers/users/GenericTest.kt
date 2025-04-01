package com.hateoasatscale.users.controllers.users

import com.hateoasatscale.users.config.UrlServiceResolver
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class GenericTest {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @MockitoBean
    protected lateinit var urlServiceResolver: UrlServiceResolver

    @BeforeEach
    fun beforeEach() {
        `when`(urlServiceResolver.getServiceUrl("users")).thenReturn(
            "http://172.20.0.4:32589"
        )
    }
}
