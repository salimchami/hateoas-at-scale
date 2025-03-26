package com.hateoasatscale.users.controllers.users

import com.hateoasatscale.users.JsonReader.strip
import com.hateoasatscale.users.JsonReader.toExpectedJson
import com.hateoasatscale.users.config.UrlServiceResolver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class UsersControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @MockitoBean
    private lateinit var urlServiceResolver: UrlServiceResolver

    @BeforeEach
    fun beforeEach() {
        `when`(urlServiceResolver.getServiceUrl("users")).thenReturn(
            "http://users")
    }

    @Test
    fun `should return user info with links depending on permissions`() {
        val expectedUser = toExpectedJson("users/user", "user-info")
        val entity = restTemplate.getForEntity<String>("/users/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }
}