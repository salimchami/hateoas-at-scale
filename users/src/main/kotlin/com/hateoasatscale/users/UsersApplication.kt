package com.hateoasatscale.users

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@AutoConfiguration
@EnableDiscoveryClient
@EnableFeignClients
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
