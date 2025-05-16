package com.hateoasatscale.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class AbstractTests {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    companion object {
        class Urls {
            companion object {
                const val USER_INFO = "/api/v1/users/user-info"
                const val ALL_USERS = "/api/v1/users/all"
            }
        }
    }
}

