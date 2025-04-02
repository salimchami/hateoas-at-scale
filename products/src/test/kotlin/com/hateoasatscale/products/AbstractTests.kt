package com.hateoasatscale.products

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.GenericContainer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
abstract class AbstractTests {

    @LocalServerPort
    protected var productsServerPort: Int = 0

    companion object {
        private val consul = GenericContainer("consul:1.9.5").withExposedPorts(8500)

        @BeforeAll
        @JvmStatic
        fun startDBContainer() {
            consul.start()
            System.setProperty("spring.cloud.consul.host", consul.host)
            System.setProperty("spring.cloud.consul.port", consul.firstMappedPort.toString())
        }

        @AfterAll
        @JvmStatic
        fun stopDBContainer() {
            consul.stop()
        }
    }

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

}

