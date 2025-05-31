package com.hateoasatscale.users

import WebConfiguration
import com.hateoasatscale.users.infrastructure.config.FeignClientConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@Import(FeignClientConfiguration::class, WebConfiguration::class)
@EnableDiscoveryClient
@EnableFeignClients
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
