package com.hateoasatscale.users.controllers.users

import com.hateoasatscale.users.JsonReader.strip
import com.hateoasatscale.users.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class UsersControllerTest : GenericTest() {

    @Test
    fun `should return user info with links`() {
        val expectedUser = toExpectedJson("users/user", "user-info")
        val entity = restTemplate.getForEntity<String>("/users/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }
}