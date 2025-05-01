package com.hateoasatscale.users.infrastructure.driving

import com.hateoasatscale.users.AbstractTests
import com.hateoasatscale.users.utils.JsonReader.strip
import com.hateoasatscale.users.utils.JsonReader.toExpectedJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

class UserResourceTest : AbstractTests() {

    @Test
    fun `should find user info with links from username`() {
        val expectedUser =
            toExpectedJson("users/user", "user-ada").replace("{{users-service-url}}", baseUrl)
        val entity = restTemplate.getForEntity<String>("$baseUrl/users/ada.lovelace")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }

    @Test
    fun `should find all user`() {
        val expectedUser =
            toExpectedJson("users", "all").replace("{{users-service-url}}", baseUrl)
        val entity = restTemplate.getForEntity<String>("$baseUrl/users/all/ada.lovelace")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body?.let { strip(it) }).isEqualTo(expectedUser)
    }
}