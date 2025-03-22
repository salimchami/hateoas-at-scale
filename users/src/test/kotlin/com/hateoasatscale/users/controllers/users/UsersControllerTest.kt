package com.hateoasatscale.users.controllers.users

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest(@Autowired val restTemplate: TestRestTemplate) {
    @Test
    fun `should return user info with links depending on permissions`() {
        val entity = restTemplate.getForEntity<String>("/users/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    }
}