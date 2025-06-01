package com.hateoasatscale.users

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableDiscoveryClient
@ComponentScan(
    basePackages = ["com.hateoasatscale.users"],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.REGEX,
            pattern = ["com.hateoasatscale.users.infrastructure.config.specific.*"],
        ),
    ],
)
@EnableFeignClients
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
