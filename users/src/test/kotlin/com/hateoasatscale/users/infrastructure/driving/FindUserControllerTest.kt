package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.AbstractTests
import com.hateoasatscale.users.utils.JsonReader.strip
import com.hateoasatscale.users.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class FindUserControllerTest : AbstractTests() {

    @Test
    fun `should return user info with links`() {
        val expectedUser = toExpectedJson("users/user", "user-ada")
        val entity = restTemplate.getForEntity<String>("/users/1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }
}